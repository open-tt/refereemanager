package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.files.FileAccess;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.ArgumentStatusType;
import de.edgesoft.refereemanager.utils.Constants;
import de.edgesoft.refereemanager.utils.TemplateHelper;

/**
 * Fill the referee list.
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
 * @version 0.6.0
 * @since 0.5.0
 */
public class RefereeList extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.5.0
	 * @since 0.5.0
	 */
	public static void main(String[] args) {
		new RefereeList().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.6.0
	 * @since 0.5.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Sends an email to the selected recipients.");
		
		addOption("p", "path", "input path of data.", true, true);
		addOption("f", "file", MessageFormat.format("input file name template (empty for {0}).", Constants.DATAFILENAMEPATTERN), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("t", "template", "template to fill.", true, true);
		addOption("o", "output", "output file (empty for template + '.out').", true, false);
		addOption("a", "status", "status (all (default), active, inactive).", true, false);
		
		init(args);
		
		listReferees(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"), getOptionValue("t"), getOptionValue("o"), getOptionValue("a"));
		
	}

	/**
	 * Fills list of referees.
	 * 
	 * @param thePath input path
	 * @param theXMLFile xml filename (null = {@link Constants#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theTemplatefile template file
	 * @param theOutputfile output file (null = template + ".out")
	 * @param theStatus status (all (default), activeonly)
	 * 
	 * @version 0.6.0
	 * @since 0.5.0
	 */
	public void listReferees(final String thePath, final String theXMLFile, final String theSeason, final String theTemplatefile, final String theOutputfile, final String theStatus) {
		
		Constants.logger.info("start.");
		
		Objects.requireNonNull(thePath, "path must not be null");
		Objects.requireNonNull(theTemplatefile, "template file must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);
		
		Path pathXMLFile = Paths.get(thePath, String.format(((theXMLFile == null) ? Constants.DATAFILENAMEPATTERN : theXMLFile), iSeason));
		
		String sOutFile = (theOutputfile == null) ? String.format("%s.out", theTemplatefile) : theOutputfile;
		
		ArgumentStatusType argStatus = ArgumentStatusType.ALL;
		try {
			argStatus = ArgumentStatusType.fromValue(theStatus);
		} catch (IllegalArgumentException e) {
			// do nothing, remains "all"
		}
		
		listReferees(pathXMLFile, Paths.get(theTemplatefile), Paths.get(sOutFile), argStatus);
		
		Constants.logger.info("stop");
		
	}
	
	/**
	 * Fills list of referees.
	 * 
	 * @param theXMLPath xml filename with path
	 * @param theTemplatePath template file
	 * @param theOutputPath output file
	 * @param theStatus status (all (default), active, inactive)
	 * 
	 * @version 0.6.0
	 * @since 0.5.0
	 */
	public void listReferees(final Path theXMLPath, final Path theTemplatePath, final Path theOutputPath, final ArgumentStatusType theStatus) {
		
		Objects.requireNonNull(theXMLPath, "xml file path must not be null");
		Objects.requireNonNull(theTemplatePath, "template file path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");
		
		try {
			
			Constants.logger.info("read template.");
			
			final List<String> lstTemplate = FileAccess.readFileInList(theTemplatePath);
			
			Constants.logger.info("read data.");
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theXMLPath.toString(), RefereeManager.class);
			
			Constants.logger.info("fill template.");
			
			List<String> lstFilled = TemplateHelper.fillTemplate(lstTemplate, mgrData, null, 0, theStatus);
			
			Constants.logger.info("write referee list.");
			
			FileAccess.writeFile(theOutputPath, lstFilled);
			
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */