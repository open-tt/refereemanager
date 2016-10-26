package de.edgesoft.refereemanager.model;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.jaxb.TrainingLevel;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;

/**
 * Referee model, additional methods for jaxb model class.
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
 * @version 0.8.0
 * @since 0.5.0
 */
public class RefereeModel extends Referee {

	/** Filter predicate for all status types. */
	public static Predicate<Referee> ALL = ref -> true;

	/** Filter predicate for active status types. */
	public static Predicate<Referee> ACTIVE = ref -> ref.getStatus().isActive();

	/** Filter predicate for inactive status types. */
	public static Predicate<Referee> INACTIVE = ref -> !ref.getStatus().isActive();

	/**
	 * Highest training level.
	 *
	 * @return highest training level
	 *
	 * @version 0.6.0
	 * @since 0.5.0
	 */
    public TrainingLevel getHighestTrainingLevel() {
    	return getTrainingLevel()
    			.stream()
    			.sorted(TrainingLevelModel.RANK.reversed())
    			.findFirst()
    			.orElse(null);
    }

	/**
	 * Local training level.
	 *
	 * @return local training level
	 *
	 * @version 0.9.0
	 * @since 0.9.0
	 */
    public TrainingLevel getLocalTrainingLevel() {
    	TrainingLevel firstLevel = getTrainingLevel()
    			.stream()
    			.sorted(TrainingLevelModel.RANK)
    			.findFirst()
    			.orElse(null);
    	
    	if ((firstLevel == null) || !firstLevel.getType().getId().equals(Prefs.get(PrefKey.LOCAL_TRAININGLEVEL))) {
    		return null;
    	}
    	
    	return firstLevel;
    }

	/**
	 * Last training update.
	 *
	 * @return last training update
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
    public LocalDate getLastTrainingUpdate() {

    	TrainingLevel highestTrainingLevel = getHighestTrainingLevel();

    	if (highestTrainingLevel == null) {
    		return null;
    	}

    	LocalDate dteReturn = highestTrainingLevel.getUpdate()
    			.stream()
    			.sorted(Comparator.reverseOrder())
    			.findFirst()
    			.orElse(null);

    	if (dteReturn == null) {
    		return highestTrainingLevel.getSince();
    	}

    	return dteReturn;
    }

	/**
	 * Next training update.
	 *
	 * @return next training update
	 *
	 * @version 0.8.0
	 * @since 0.8.0
	 */
    public LocalDate getNextTrainingUpdate() {

    	LocalDate lastTrainingUpdate = getLastTrainingUpdate();

    	if (lastTrainingUpdate == null) {
    		return null;
    	}

    	return lastTrainingUpdate.plusYears(getHighestTrainingLevel().getType().getUpdateInterval());
    }

    /**
     * Does referee receive docs by letter?.
     *
     * Considers property and lack of email address.
     *
     * @return receive docs by letter?
	 *
	 * @version 0.8.0
	 * @since 0.8.0
     */
    @Override
    public boolean isDocsByLetter() {
        return docsByLetter || getEMail().isEmpty();
    }

}

/* EOF */
