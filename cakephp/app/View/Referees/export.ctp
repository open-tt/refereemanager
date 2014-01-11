<?php

	if ($type === 'excel') {

		// compute different styles
		foreach ($statustypes as &$statustypeedit) {
			$statustypeedit['outputstyle'] = array();

			if ($statustypeedit['style']) {
				switch ($statustypeedit['style']) {
					case 'normal':
					case 'italic':
					case 'oblique':
						$statustypeedit['outputstyle']['font-style'] = $statustypeedit['style'];
						break;
					case 'normal':
					case 'bold':
					case 'bolder':
					case 'lighter':
						$statustypeedit['outputstyle']['font-weight'] = $statustypeedit['style'];
						break;
				}
			}

			if ($statustypeedit['color']) {
				$statustypeedit['outputstyle']['color'] = $statustypeedit['color'];
			}

			if ($statustypeedit['bgcolor']) {
				$statustypeedit['outputstyle']['bg-color'] = $statustypeedit['bgcolor'];
			}
		}

		// start table
		$this->PHPExcel->createWorksheet(null, 11, PHPExcel_Style_Alignment::VERTICAL_TOP, PHPExcel_Style_Alignment::HORIZONTAL_LEFT);

		// meta information
		$this->PHPExcel->addTableRow(array('text' => array(__('Verbandsschiedsrichter BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))), array('font-weight' => 'normal', 'font-size' => 14));
		$this->PHPExcel->addTableRow(array());

		$this->PHPExcel->getXLS()->getProperties()
				->setCreator(__('RefereeManager'))
				->setLastModifiedBy(__('RefereeManager'))
				->setTitle(__('Verbandsschiedsrichter des BTTV Saison %s, Stand: %s', $season['title_season'], $this->RefereeFormat->formatDate(time(), 'date')))
				->setSubject(__('Verbandsschiedsrichter des BTTV'))
				->setDescription(__('Übersicht der Verbandsschiedsrichter, exportiert aus dem RefereeManager'))
				->setKeywords(__('Verbandsschiedsrichter BTTV %s', $season['title_season']))
				->setCategory(__('Schiedsrichterliste'));

		// header
		$header = array();
			$header[] = array('text' => __('Name'), 'width' => '15');
			$header[] = array('text' => __('Vorname'));

			foreach ($refereerelationtypes as $sid => $refereerelationtype) {
				if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
					$header[] = array('text' => __($refereerelationtype['title']));
				}
			}

			if ($isReferee) {
				$header[] = array('text' => __('E-Mail'));
				$header[] = array('text' => __('Telefon'));
			}

			if ($isEditor) {
				$header[] = array('text' => __('Adresse'));
				$header[] = array('text' => __('Geschlecht'));
				$header[] = array('text' => __('Geburtstag'));
			}

			$header[] = array('text' => __('Ausbildung'));

			if ($isEditor) {
				$header[] = array('text' => __('Letzte Fortbildung'));
				$header[] = array('text' => __('Nächste Fortbildung'));
				$header[] = array('text' => __('Anmerkung'));
			}

		$this->PHPExcel->addTableHeader($header, array('font-weight' => 'bold', 'font-size' => 10, 'width' => 'auto'), true);

		if (empty($referees)) {
			$this->PHPExcel->addTableTexts(__('Es sind keine Schiedsrichter_innen gespeichert.'));
		} else {
			// datarows
			foreach ($referees as $referee) {

				$refformat = array();

				$datarow = array();
				$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'name_title'));
				$datarow[] = array('text' => $this->RefereeFormat->formatPerson($referee['Person'], 'first_name'));

				// relations
				foreach ($refereerelationtypes as $sid => $refereerelationtype) {
					if (($sid == RefereeRelationType::SID_MEMBER) || ($sid == RefereeRelationType::SID_REFFOR) || $isEditor) {
						$text = '';
						$hasMore = false;
						if (array_key_exists($sid, $referee['RefereeRelation'])) {
							foreach ($referee['RefereeRelation'][$sid] as $refereerelation) {
								if ($hasMore) {
									$text .= '; ';
								}
								if (array_key_exists('Club', $refereerelation)) {
									$text .= $refereerelation['Club']['name'];
								}
								if (array_key_exists('League', $refereerelation)) {
									$text .= $refereerelation['League']['title'];
								}
								$hasMore = true;
							}
						}
						$datarow[] = array('text' => $text);
					}
				}

				if ($isReferee) {
					// email
					$text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('Email', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Email']) > 1);
						foreach ($referee['Contact']['Email'] as $contacttype => $emailkind) {
							$printType |= (count($emailkind) > 1);
							foreach ($emailkind as $email) {
								if ($hasMore) {
									$text .= "\n";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$text .=  __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$text .= $this->RefereeFormat->formatEMail($email, 'text');
								$hasMore = true;
							}
						}
					}
					$datarow[] = array('text' => $text);

					// phone
					$text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('PhoneNumber', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['PhoneNumber']) > 1);
						foreach ($referee['Contact']['PhoneNumber'] as $contacttype => $phonekind) {
							$printType |= (count($phonekind) > 1);
							foreach ($phonekind as $phone) {
								if ($hasMore) {
									$text .= "\n";
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$text .= $this->RefereeFormat->formatPhone($phone, 'normal');
								$hasMore = true;
							}
						}
					}
					$datarow[] = array('text' => $text);
				}

				if ($isEditor) {
					// address
					$text = '';
					if (array_key_exists('Contact', $referee) && array_key_exists('Address', $referee['Contact'])) {
						$hasMore = false;
						$printType = (count($referee['Contact']['Address']) > 1);
						foreach ($referee['Contact']['Address'] as $contacttype => $addresskind) {
							$printType |= (count($addresskind) > 1);
							foreach ($addresskind as $address) {
								if ($hasMore) {
									$text .= '\n';
								}
								if ($printType || ($contacttype != Configure::read('RefMan.defaultcontacttypeid'))) {
									$text .= __('%s: ', $contacttypes[$contacttype]['abbreviation']);
								}
								$text .= $this->RefereeFormat->formatAddress($address, 'fulladdress');
								$hasMore = true;
							}
						}
					}
					$datarow[] = array('text' => $text);

					// sex
					$datarow[] = array('text' => __($referee['SexType']['title']));

					// birthday
					$text = '';
					if (!empty($referee['Person']['birthday'])) {
						$text .= $this->RefereeFormat->formatPerson($referee['Person'], 'birthday');
					}
					$datarow[] = array('text' => $text);
				}

				// training level
				$text = '';
				if (!empty($referee['TrainingLevelInfo'])) {
					$text .= __($referee['TrainingLevelInfo']['abbreviation']);
				}
				$datarow[] = array('text' => $text);

				if ($isEditor) {
					// last update
					$text = '';
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['lastupdate'])) {
						$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['lastupdate'], 'date');
					}
					$datarow[] = array('text' => $text);

					// next update
					$text = '';
					if (!empty($referee['TrainingLevelInfo']) && !empty($referee['TrainingLevelInfo']['nextupdate'])) {
						$text .= $this->RefereeFormat->formatDate($referee['TrainingLevelInfo']['nextupdate'], 'year');
					}
					$datarow[] = array('text' => $text);

					$datarow[] = array('text' => (empty($referee['Person']['remark'])) ? '' : __($referee['Person']['remark']));

				}

				$this->PHPExcel->addTableRow($datarow, $statustypes[$referee['RefereeStatus']['sid']]['outputstyle']);
			}

			// legend
			$this->PHPExcel->addTableRow(array());
			$this->PHPExcel->addTableRow(array('text' => array(__('Legende:'))), array('font-weight' => 'bold', 'font-size' => 10));
			foreach ($statustypes as $statustype) {
				if (($statustype['sid'] == StatusType::SID_MANY) ||
						($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
						($statustype['sid'] == StatusType::SID_OTHER)) {
					$this->PHPExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])), $statustype['outputstyle']);
				}
			}

			$this->PHPExcel->addTableRow(array());
			$this->PHPExcel->addTableRow(array('text' => array(__('Zusatzinformationen'))), array('font-weight' => 'bold', 'font-size' => 10));
			foreach ($statustypes as $statustype) {
				if (($statustype['sid'] == StatusType::SID_MANY) ||
						($statustype['sid'] == StatusType::SID_INACTIVESEASON) ||
						($statustype['sid'] == StatusType::SID_OTHER)) {
					$this->PHPExcel->addTableRow(array(array('text' => ($statustype['remark']) ? $statustype['remark'] : $statustype['title'])), array('font-weight' => 'bold', 'font-size' => 10));

					$hasMore = false;
					$text = '';
					foreach ($statustype['referees'] as $referee) {
						if ($hasMore) {
							$text .= ', ';
						}
						$text .= __($this->RefereeFormat->formatPerson($referee, 'fullname'));
						$hasMore = true;
					}
					$this->PHPExcel->addTableTexts($text);
				}
			}
		}

		// output
		$this->PHPExcel->output('VSR.xlsx');

	} else if ($type === 'zip') {

		// just a simple test, nothing important right now
		$zip = new ZipArchive();
		$res = $zip->open('test.zip', ZipArchive::CREATE);
		if ($res === TRUE) {
			$zip->addFromString('file1', 'content 1');
			$zip->addFromString('file2', 'content 2');
			$zip->close();
			echo 'ok';
		} else {
			echo 'failed';
		}

	} else {
		throw new CakeException(__('Exporttyp "%s" nicht unterstützt!', $type));
	}

?>

