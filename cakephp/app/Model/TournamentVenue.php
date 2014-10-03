<?php

App::uses('AppModel', 'Model');

/**
 * TournamentVenue Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.1
 * @since 0.1
 */
class TournamentVenue extends AppModel {

	/**
	 * Declare virtual display field in constructor to be alias-safe.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function __construct($id = false, $table = null, $ds = null) {
		parent::__construct($id, $table, $ds);
		// I can't get this one working with other tables
		$this->virtualFields['display_tournament_venue'] = sprintf(
			'CONCAT(%1$s.id, "-", %1$s.tournament_id, "-", %1$s.contact_id)',
			$this->alias
		);
	}

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'TournamentVenue';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'display_tournament_venue';

	/**
	 * Validation rules
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array(
			'uuid' => array(
				'rule' => array('uuid'),
			),
		),
		'tournament_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
		'contact_id' => array(
			'numeric' => array(
				'rule' => array('numeric'),
			),
		),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Contact', 'Tournament');

}

/* EOF */

