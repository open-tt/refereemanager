package de.edgesoft.refereemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

import de.edgesoft.edgeutils.commandline.AbstractMainClass;
import de.edgesoft.edgeutils.commons.ext.VersionExt;
import de.edgesoft.edgeutils.files.JAXBFiles;
import de.edgesoft.refereemanager.jaxb.ObjectFactory;
import de.edgesoft.refereemanager.jaxb.RefereeManager;
import de.edgesoft.refereemanager.model.SeasonModel;
import de.edgesoft.refereemanager.utils.ArgumentDBOperation;
import de.edgesoft.refereemanager.utils.Constants;

/**
 * Database operations.
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
 * @version 0.7.0
 * @since 0.7.0
 */
public class DBOperations extends AbstractMainClass {
	
	/**
	 * Command line entry point.
	 * 
	 * @param args command line arguments
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public static void main(String[] args) {
		new DBOperations().executeOperation(args);
	}

	/**
	 * Programmatic entry point, initializing and executing main functionality.
	 * 
	 * - set description setDescription("...");
	 * - add options addOption("short", "long", "description", argument?, required?);
	 * - call init(args);
	 * - call operation execution with arguments
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	@Override
	public void executeOperation(final String[] args) {
		
		setDescription("Sends an email to the selected recipients.");
		
		addOption("p", "path", "input path of data.", true, true);
		addOption("f", "file", MessageFormat.format("input file name template (empty for {0}).", Constants.DATAFILENAMEPATTERN), true, false);
		addOption("s", "season", "season (empty for current season).", true, false);
		addOption("o", "output", "output file (empty for database file + '.db.xml').", true, false);
		addOption("d", "dboperation", "database operation (sort).", true, true);
		
		init(args);
		
		dbOperation(getOptionValue("p"), getOptionValue("f"), getOptionValue("s"), getOptionValue("o"), getOptionValue("d"));
		
	}

	/**
	 * Executes database operation.
	 * 
	 * @param thePath input path
	 * @param theXMLFile xml filename (null = {@link Constants#DATAFILENAMEPATTERN})
	 * @param theSeason season (null = current season)
	 * @param theOutputfile output file (null = template + ".db.xml")
	 * @param theDBOperation database operation
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public void dbOperation(final String thePath, final String theXMLFile, final String theSeason, final String theOutputfile, final String theDBOperation) {
		
		Constants.logger.info("start.");
		
		Objects.requireNonNull(thePath, "path must not be null");
		Objects.requireNonNull(theDBOperation, "db operation must not be null");
		
		Integer iSeason = (theSeason == null) ? SeasonModel.getCurrentStartYear() : Integer.valueOf(theSeason);
		
		Path pathXMLFile = Paths.get(thePath, String.format(((theXMLFile == null) ? Constants.DATAFILENAMEPATTERN : theXMLFile), iSeason));
		
		String sOutFile = (theOutputfile == null) ? String.format("%s.db.xml", pathXMLFile) : theOutputfile;
		
		ArgumentDBOperation argDBOperation = ArgumentDBOperation.fromValue(theDBOperation);
		
		dbOperation(pathXMLFile, Paths.get(sOutFile), argDBOperation);
		
		Constants.logger.info("stop");
		
	}
	
	/**
	 * Executes database operation.
	 * 
	 * @param theXMLPath xml filename with path
	 * @param theTemplatePath template file
	 * @param theOutputPath output file
	 * @param theDBOperation database operation
	 * 
	 * @version 0.7.0
	 * @since 0.7.0
	 */
	public void dbOperation(final Path theXMLPath, final Path theOutputPath, final ArgumentDBOperation theDBOperation) {
		
		Objects.requireNonNull(theXMLPath, "xml file path must not be null");
		Objects.requireNonNull(theOutputPath, "output file path must not be null");
		Objects.requireNonNull(theDBOperation, "db operation must not be null");
		
		try {
			
			Constants.logger.info(String.format("read database '%s'.", theXMLPath.toString()));
			
			final RefereeManager mgrData = JAXBFiles.unmarshal(theXMLPath.toString(), RefereeManager.class);
			
			Constants.logger.info(String.format("execute operation '%s'.", theDBOperation.value()));
			
			// do something
			switch (theDBOperation) {
				case REMOVECLUBS:
					de.edgesoft.refereemanager.utils.DBOperations.removeClubs(mgrData);
					break;
				case SORT:
					de.edgesoft.refereemanager.utils.DBOperations.sortDB(mgrData);
					break;
			}
			
			// update info block
			mgrData.getInfo().setModified(LocalDateTime.now());
			mgrData.getInfo().setDocversion(new VersionExt(Constants.DOCVERSION));
			mgrData.getInfo().setAppversion(new VersionExt(Constants.APPVERSION));
			mgrData.getInfo().setCreator(this.getClass().getName());
			
			Constants.logger.info(String.format("write database '%s'.", theOutputPath.toString()));
			
			JAXBFiles.marshal(new ObjectFactory().createRefereemanager(mgrData), theOutputPath.toString(), "../../../git/refereemanager/java-backend/refereemanager/src/main/jaxb/refereemanager.xsd");
			
			
		} catch (Exception e) {
			Constants.logger.error(e);
			e.printStackTrace();
		}
		
	}
	
}

/* EOF */