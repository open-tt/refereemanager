<?php

App::uses('AppController', 'Controller');
App::uses('CakeEmail', 'Network/Email');
App::uses('RefManPeople', 'Utility');
App::uses('RefManRefereeFormat', 'Utility');
App::uses('RefManTemplate', 'Utility');

/**
 * Tools editor Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class ToolsEditorController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeFormat');

	/** Models. */
	public $uses = array('Referee', 'Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		if (!$this->viewVars['isEditor']) {
			$this->Session->setFlash(__('Der Zugriff ist nur für Editoren erlaubt. Bitte loggen Sie sich ein.'));
			$this->redirect(array('controller' => 'users', 'action' => 'login'));
		}
	}

	/**
	 * Index method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function index() {
		$this->set('title_for_layout', __('Editor-Werkzeuge'));
	}

	/**
	 * Mailinglist method.
	 *
	 * @param season season to use (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function mailinglist($season = null) {

		$this->getSeason($season);
		$this->setAndGetStandard($this->viewVars['season']);

		$theSeparator = ',';
		if (!empty($this->request->data) && array_key_exists('ToolsEditor', $this->request->data)) {
			$theSeparator = $this->request->data['ToolsEditor']['separator'];
		}

		$this->set('separator', $theSeparator);

		$this->set('title_for_layout', __('Mailverteiler'));
	}

	/**
	 * Message method.
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function message() {

		$this->set('title_for_layout', __('Nachricht'));

		$this->getSeason(null);
		$this->setAndGetStandard($this->viewVars['season']);

		// create messages
		if (!empty($this->request->data)) {

			$this->set('messagedata', $this->request->data);

			$messageresult = array();

			$toMeOnly = $this->request->data['ToolsEditor']['recipient'] === 'i';
			$sendEmail = ($this->request->data['ToolsEditor']['recipient'] === 'a') ||
					($this->request->data['ToolsEditor']['recipient'] === 'm') ||
					$toMeOnly;
			$sendLetter = ($this->request->data['ToolsEditor']['recipient'] === 'a') ||
					($this->request->data['ToolsEditor']['recipient'] === 's') ||
					$toMeOnly;

			$sendSingleEmails = $this->request->data['ToolsEditor']['mailkind'] === 's';

			// if attachment: does it exist?
			$attachmentOK = true;
			if ($sendEmail && !empty($this->request->data['ToolsEditor']['attachment'])) {
				$attachment = sprintf('%s%s', TMP, $this->request->data['ToolsEditor']['attachment']);
				if (!file_exists($attachment)) {
					$this->Session->setFlash(__('Dateianhang "%s" existiert nicht. Keine Nachrichten wurden versendet.', $attachment));
					$attachmentOK = false;
				}
			}

			if ($attachmentOK) {

				// read templates
				$tplEmail = RefManTemplate::getTemplate('email');
				$tplLetter = RefManTemplate::getTemplate('letter');

				// start zip archive for letters
				if ($sendLetter) {
					RefManTemplate::openZip(Configure::read('RefMan.template.letterout'));
				}

				// fill templates with form values
				$tplEmail = RefManTemplate::replace($tplEmail, 'body', $this->request->data['ToolsEditor']['body']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'body', $this->request->data['ToolsEditor']['body']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'opening', $this->request->data['ToolsEditor']['opening']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'opening', $this->request->data['ToolsEditor']['opening']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'closing', $this->request->data['ToolsEditor']['closing']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'closing', $this->request->data['ToolsEditor']['closing']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'signature', $this->request->data['ToolsEditor']['signature']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'signature', $this->request->data['ToolsEditor']['signature']);

				$tplLetter = RefManTemplate::replace($tplLetter, 'subject', $this->request->data['ToolsEditor']['subject']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'date', RefManRefereeFormat::formatDate(time(), 'medium'));

				$arrEmails = array();
				$arrLetter = array();

				// referees to send messages to
				$hasMe = false;
				if (!$toMeOnly) {
					foreach ($this->viewVars['referees'] as $ref) {
						$arrReferees[] = $ref;
						if ($this->viewVars['userpersonid'] == $ref['Person']['id']) {
							$hasMe = true;
						}
					}
				}

				if (!$hasMe) {
					$arrReferees[] = $this->Referee->getRefereeByPersonId($this->viewVars['userpersonid']);
				}

				// fill templates with person values
				foreach ($arrReferees as $referee) {

					$contactEmail = RefManPeople::getPrimaryContact($referee, 'Email');
					if ($sendEmail && !empty($contactEmail)) {

						$txtEmail = RefManTemplate::replaceRefereeData($tplEmail, $referee);

						// send email (set up email config correctly)
						$Email = new CakeEmail('default');
						$Email
								->to($contactEmail['Email']['email'])
								->subject($this->request->data['ToolsEditor']['subject']);
						if (isset($attachment)) {
							$Email->attachments($attachment);
						}

						if (empty($this->request->data['ToolsEditor']['test_only'])) {
							$Email->send($txtEmail);

							CakeLog::write('email', __('Mail sent to %s <%s>.',
																				 RefManRefereeFormat::formatPerson($referee, 'fullname'),
																				 $contactEmail['Email']['email']));
						}

						// output for user
						$arrEmails[] = sprintf('%s &lt;%s&gt;',
																	 RefManRefereeFormat::formatPerson($referee, 'fullname'),
																	 $contactEmail['Email']['email']);
					}

					$contactAddress = RefManPeople::getPrimaryContact($referee, 'Address');
					if ($sendLetter && !empty($contactAddress) &&
							(empty($contactEmail) || ($referee['Referee']['docs_per_letter'] === true))) {

						$txtLetter = RefManTemplate::replaceRefereeData($tplLetter, $referee);
						$txtLetter = RefManTemplate::replace($txtLetter, 'streetnumber', RefManRefereeFormat::formatAddress($contactAddress, 'streetnumber', 'text'));
						$txtLetter = RefManTemplate::replace($txtLetter, 'zipcity', RefManRefereeFormat::formatAddress($contactAddress, 'zipcity', 'text'));

						// store letter
						RefManTemplate::addToZip('mmd',
																		 sprintf('%s_%s',
																						 RefManTemplate::fileName($referee['Person']['name']),
																						 RefManTemplate::fileName($referee['Person']['first_name'])),
																		 $txtLetter);

						// output for user
						CakeLog::write('letter', __('Letter generated for %s.', RefManRefereeFormat::formatPerson($referee, 'fullname')));
						$arrLetter[] = sprintf('%s', RefManRefereeFormat::formatPerson($referee, 'fullname'));
					}

				}

				// save zip archive for letters
				if ($sendLetter) {
					$zipfile = RefManTemplate::closeZip();
				}

				$messageresult[] = __('Emaillogs in "%slogs/email.log".', TMP);
				$messageresult[] = __('Brieflogs in "%slogs/letter.log".', TMP);

				if (empty($arrEmails)) {
					$messageresult[] = __('Keine Emails.');
				} else {
					if (!empty($this->request->data['ToolsEditor']['test_only'])) {
						$messageresult[] = __('Testmodus: keine Emails versendet.');
					}
					$messageresult[] = __('Emails (%d) an: %s.',
																count($arrEmails),
																RefManRefereeFormat::formatMultiline($arrEmails, ', '));
				}
				if (empty($arrLetter)) {
					$messageresult[] = __('Keine Briefe.');
				} else {
					$messageresult[] = __('Briefe (%d), abgelegt in "%s" an: %s.',
																count($arrLetter),
																$zipfile,
																RefManRefereeFormat::formatMultiline($arrLetter, ', '));
				}

				$this->set('messageresult', $messageresult);

			}

		}

	}

	/**
	 * Set and get standard values.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandard($season = null) {

		$theSeason = $this->getSeason($season);
		$this->set('seasonarray', $this->Season->getSeasonList($this->viewVars['isEditor']));

		$referees = $this->Referee->getReferees($theSeason);
		$this->set('referees', $referees);

		$this->set('controller', 'ToolsEditor');
	}

}

/* EOF */

