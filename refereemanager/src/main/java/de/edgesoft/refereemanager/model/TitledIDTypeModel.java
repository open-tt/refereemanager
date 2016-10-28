package de.edgesoft.refereemanager.model;

import java.text.Collator;
import java.util.Comparator;

import de.edgesoft.refereemanager.jaxb.TitledIDType;
import javafx.beans.property.SimpleStringProperty;

/**
 * TitledIDType model, additional methods for jaxb model class.
 *
 * ## Legal stuff
 *
 * Copyright 2016-2016 Ekkart Kleinod <ekleinod@edgesoft.de>
 *
 * This file is part of refereemanager.
 *
 * refereemanager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * refereemanager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author Ekkart Kleinod
 * @version 0.10.0
 * @since 0.5.0
 */
public class TitledIDTypeModel extends TitledIDType {

	/**
	 * Comparator title.
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public static final Comparator<TitledIDType> TITLE = Comparator.comparing(titled -> titled.getTitle().get(), Collator.getInstance());

	/**
	 * Comparator shorttitle.
	 *
	 * @todo not sorted with collator
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public static final Comparator<TitledIDType> SHORTTITLE = Comparator.comparing(titled -> titled.getShorttitle().get(), Comparator.nullsFirst(String::compareTo));

	/**
	 * Comparator shorttitle then title.
	 *
	 * @todo does not work with shorttitle == null
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
	public static final Comparator<TitledIDType> SHORTTITLE_TITLE = SHORTTITLE.thenComparing(TITLE);

	/**
	 * Comparator displaytitle.
	 *
	 * @version 0.10.0
	 * @since 0.9.0
	 */
	public static final Comparator<TitledIDTypeModel> DISPLAYTITLE = Comparator.comparing(titled -> titled.getDisplayTitle().get(), Collator.getInstance());

	/**
	 * Display title.
	 *
	 * @return display title
	 *
	 * @version 0.10.0
	 * @since 0.6.0
	 */
    public SimpleStringProperty getDisplayTitle() {
    	return (getShorttitle() == null) ? ((getTitle() == null) ? new SimpleStringProperty(getId()) : getTitle()) : getShorttitle();
    }

}

/* EOF */
