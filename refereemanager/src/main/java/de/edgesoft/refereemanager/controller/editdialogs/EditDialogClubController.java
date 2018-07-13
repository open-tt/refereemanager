package de.edgesoft.refereemanager.controller.editdialogs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import de.edgesoft.edgeutils.commons.IDType;
import de.edgesoft.refereemanager.controller.crud.ListCRUDController;
import de.edgesoft.refereemanager.controller.inputforms.IInputFormController;
import de.edgesoft.refereemanager.jaxb.Club;
import de.edgesoft.refereemanager.jaxb.TitledIDType;
import de.edgesoft.refereemanager.jaxb.URL;
import de.edgesoft.refereemanager.jaxb.Venue;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.utils.JAXBMatch;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Controller for the club edit dialog scene.
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
 * @version 0.15.0
 * @since 0.15.0
 */
public class EditDialogClubController extends AbstractTabbedEditDialogController<Club> {

	/**
	 * Titled id type data.
	 */
	@FXML
	private Parent embeddedInputFormTitledIDTypeData;

	/**
	 * Titled id data controller.
	 */
	@FXML
	private IInputFormController<Club> embeddedInputFormTitledIDTypeDataController;

	/**
	 * Club data.
	 */
	@FXML
	private Parent embeddedInputFormClubData;

	/**
	 * Club data controller.
	 */
	@FXML
	private IInputFormController<Club> embeddedInputFormClubDataController;

//	/**
//	 * CRUD buttons url.
//	 */
//	@FXML
//	private Parent embeddedCRUDURL;
//
//	/**
//	 * CRUD buttons url controller.
//	 */
//	@FXML
//	@JAXBMatch(jaxbfield = "URL", jaxbclass = Club.class)
//	protected ListCRUDController<URL> embeddedCRUDURLController;
//
	/**
	 * Venue data.
	 */
	@FXML
	private Parent embeddedInputFormVenueData;

	/**
	 * Venue data controller.
	 */
	@FXML
	private IInputFormController<Club> embeddedInputFormVenueDataController;


	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 */
	@FXML
	@Override
	protected void initialize() {

		addInputFormController(embeddedInputFormTitledIDTypeDataController);
		addInputFormController(embeddedInputFormClubDataController);
		addInputFormController(embeddedInputFormVenueDataController);

//		Map.Entry<Parent, FXMLLoader> nodeURL = Resources.loadNode("inputforms/PartInputFormURL");
//		embeddedCRUDURLController.initController(
//				nodeURL.getValue().getController(),
//				nodeURL.getKey(),
//				"URL (ToDo)",
//				AppModel.factory::createURL);

		initForm(new ArrayList<>(Arrays.asList(new Class<?>[]{IDType.class, TitledIDType.class, Club.class, URL.class, Venue.class})));

		super.initialize();

	}

}

/* EOF */
