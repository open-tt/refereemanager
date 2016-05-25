<?php

App::uses('AppModel', 'Model');

/**
 * Email Model
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class Email extends AppModel {

	/**
	 * Model name.
	 *
	 * Good practice to include the model name.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $name = 'Email';

	/**
	 * Display field
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $displayField = 'email';

	/**
	 * Validation rules
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public $validate = array(
		'id' => array('isUnique', 'notblank', 'numeric'),
		'contact_id' => array('isUnique', 'notblank', 'numeric'),
		'email' => array('email', 'notblank'),
	);

	/**
	 * belongsTo associations
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public $belongsTo = array('Contact');

}

/* EOF */

