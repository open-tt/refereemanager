<?php

App::uses('AppModel', 'Model');

/**
 * RefereeRelationType Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class RefereeRelationType extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'RefereeRelationType';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'title';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notempty', 'numeric'),
		'sid' => array('isUnique', 'notempty'),
		'title' => array('isUnique', 'notempty'),
	);

	/**
	 * hasMany associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $hasMany = array('RefereeRelation');

	// custom programming

	/* Relation types. */
	const SID_MEMBER = 'member';
	const SID_REFFOR = 'reffor';
	const SID_PREFER = 'prefer';
	const SID_NOASSIGNMENT = 'noassignment';

	/** Singleton for fast access. */
	private $refereerelationtypes = null;

	/**
	 * Returns referee relation types.
	 *
	 * Method should be static,
	 * maybe later when I understand how to find things in a static method
	 *
	 * @return array of referee relation types, empty if there are none
	 *
	 * @version 0.3
	 * @since 0.3
	 */
	public function getRefereeRelationTypes() {
		if ($this->refereerelationtypes == null) {
			$this->recursive = -1;
			$this->refereerelationtypes = array();
			foreach ($this->find('all') as $refereerelationtype) {
				$this->refereerelationtypes[$refereerelationtype['RefereeRelationType']['id']] = $refereerelationtype;
			}
//			usort($this->refereerelationtypes, array('RefereeRelationType', 'compareTo'));
		}

		return $this->refereerelationtypes;
	}

}

/* EOF */

