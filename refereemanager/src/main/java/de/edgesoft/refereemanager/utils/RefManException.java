package de.edgesoft.refereemanager.utils;

/**
 * Special exception (good coding style, I presume).
 *
 * ## Legal stuff
 *
 * Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
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
 * @version 0.14.0
 * @since 0.5.0
 */
public class RefManException extends Exception {

	/** Default serial id. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor with message.
	 *
	 * @param theErrorMessage error message
	 *
	 * @version 0.14.0
	 * @since 0.5.0
	 */
	public RefManException(String theErrorMessage) {
		super(theErrorMessage);
	}

}

/* EOF */
