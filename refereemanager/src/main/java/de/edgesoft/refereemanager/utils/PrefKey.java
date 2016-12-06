
package de.edgesoft.refereemanager.utils;

/**
 * Preference keys.
 *
 * For enums I use the coding style of jaxb, so there will be no inconsistencies.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of TT-Schiri: Referee Manager.
 *
 * TT-Schiri: Referee Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TT-Schiri: Referee Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TT-Schiri: Referee Manager. If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.12.0
 * @since 0.8.0
 */
public enum PrefKey {

	AREA_CODE,

	COUNTRY_CODE,
	CONTACT_PRIVATE,

	DOCUMENTS_TEMPLATE_DOCUMENT,

	EMAIL_FROM_EMAIL,
	EMAIL_FROM_NAME,
	EMAIL_SMTP_HOST,
	EMAIL_SMTP_PASSWORD,
	EMAIL_SMTP_USERNAME,
	EMAIL_TEMPLATE_EMAIL,
	EMAIL_TEMPLATE_LETTER,
	EMAIL_TEMPLATE_MERGE_SINGLE,
	EMAIL_TEMPLATE_MERGE_ALL,
	EMAIL_TO_EMAIL,
	EMAIL_TO_NAME,

	FILE,

	FILENAME_PATTERN_DATABASE,
	FILENAME_PATTERN_REFEREE_DATA,
	FILENAME_PATTERN_REFEREE_MERGE,
	FILENAME_PATTERN_REFEREES_MERGE,

	IMAGE_PATH,

	LOCAL_TRAININGLEVEL,

	LOCALE,

	PATH,
	
	PREFERENCES_FILE,

	REFEREE_COMMUNICATION_FILE,
	REFEREE_COMMUNICATION_OUTPUT_PATH,
	REFEREE_COMMUNICATION_LAST_ATTACHMENT_PATH,
	REFEREE_COMMUNICATION_SPLIT_0,
	REFEREE_COMMUNICATION_SPLIT_1,

	REFEREE_OVERVIEW_SPLIT,

	STAGE_HEIGHT,
	STAGE_MAXIMIZED,
	STAGE_WIDTH,
	STAGE_X,
	STAGE_Y,

	TEMPLATE_PATH,

	TITLE_FULLPATH,
	;

		private final String value;

		PrefKey() {
				value = name().toLowerCase();
		}

		public String value() {
				return value;
		}

		public static PrefKey fromValue(String v) {
				for (PrefKey c: PrefKey.values()) {
						if (c.value.equals(v)) {
								return c;
						}
				}
				throw new IllegalArgumentException(v);
		}

}

/* EOF */
