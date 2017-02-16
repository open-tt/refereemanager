package de.edgesoft.refereemanager.controller;

import java.io.File;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Map;

import de.edgesoft.edgeutils.datetime.DateTimeUtils;
import de.edgesoft.refereemanager.jaxb.Referee;
import de.edgesoft.refereemanager.model.AppModel;
import de.edgesoft.refereemanager.model.ContentModel;
import de.edgesoft.refereemanager.model.RefereeModel;
import de.edgesoft.refereemanager.utils.AlertUtils;
import de.edgesoft.refereemanager.utils.PrefKey;
import de.edgesoft.refereemanager.utils.Prefs;
import de.edgesoft.refereemanager.utils.Resources;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the referee list scene.
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
 * @version 0.13.0
 * @since 0.10.0
 */
public class RefereeOverviewController {

	/**
	 * Heading label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblHeading;

	/**
	 * Name label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblName;

	/**
	 * Name label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblNameLabel;

	/**
	 * First name label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblFirstName;

	/**
	 * First name label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblFirstNameLabel;

	/**
	 * Training level label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblTrainingLevel;

	/**
	 * Training level label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblTrainingLevelLabel;

	/**
	 * Club label.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Label lblClub;

	/**
	 * Club label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblClubLabel;

	/**
	 * Birthday label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblBirthday;

	/**
	 * Birthday label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblBirthdayLabel;

	/**
	 * Last update label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblLastUpdate;

	/**
	 * Last update label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblLastUpdateLabel;

	/**
	 * Next update label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblNextUpdate;

	/**
	 * Next update label label.
	 *
	 * @version 0.13.0
	 * @since 0.13.0
	 */
	@FXML
	private Label lblNextUpdateLabel;

	/**
	 * Referee image.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private ImageView imgReferee;

	/**
	 * Add button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnAdd;

	/**
	 * Edit button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnEdit;

	/**
	 * Delete button.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private Button btnDelete;

	/**
	 * Split pane.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private SplitPane pneSplit;


	/**
	 * Main app controller.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private AppLayoutController appController = null;


	/**
	 * Referee list controller.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private RefereeListController ctlRefList;

	/**
	 * Initializes the controller class.
	 *
	 * This method is automatically called after the fxml file has been loaded.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void initialize() {

		// list
		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeList");
		AnchorPane refList = (AnchorPane) pneLoad.getKey();

		// add referee list to split pane
		pneSplit.getItems().add(0, refList);

		// store referee table controller
		ctlRefList = pneLoad.getValue().getController();

		// clear event details
		showDetails(null);

		// listen to selection changes, show event
		ctlRefList.getRefereesSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));

		// enabling edit/delete buttons only with selection
		btnEdit.disableProperty().bind(ctlRefList.getRefereesSelectionModel().selectedItemProperty().isNull());
		btnDelete.disableProperty().bind(ctlRefList.getRefereesSelectionModel().selectedItemProperty().isNull());

		// disabling labels
		ObservableBooleanValue obsReferees = ctlRefList.getTabReferees().selectedProperty();
		lblTrainingLevelLabel.visibleProperty().bind(obsReferees);
		lblTrainingLevelLabel.managedProperty().bind(obsReferees);
		lblTrainingLevel.visibleProperty().bind(obsReferees);
		lblTrainingLevel.managedProperty().bind(obsReferees);
		lblLastUpdateLabel.visibleProperty().bind(obsReferees);
		lblLastUpdateLabel.managedProperty().bind(obsReferees);
		lblLastUpdate.visibleProperty().bind(obsReferees);
		lblLastUpdate.managedProperty().bind(obsReferees);
		lblNextUpdateLabel.visibleProperty().bind(obsReferees);
		lblNextUpdateLabel.managedProperty().bind(obsReferees);
		lblNextUpdate.visibleProperty().bind(obsReferees);
		lblNextUpdate.managedProperty().bind(obsReferees);

		ObservableBooleanValue obsRefereesOrTrainees = ctlRefList.getTabReferees().selectedProperty().or(ctlRefList.getTabTrainees().selectedProperty());
		lblClubLabel.visibleProperty().bind(obsRefereesOrTrainees);
		lblClubLabel.managedProperty().bind(obsRefereesOrTrainees);
		lblClub.visibleProperty().bind(obsRefereesOrTrainees);
		lblClub.managedProperty().bind(obsRefereesOrTrainees);


		// set divider position
		pneSplit.setDividerPositions(Double.parseDouble(Prefs.get(PrefKey.REFEREE_OVERVIEW_SPLIT)));

		// if changed, save divider position to preferences
		pneSplit.getDividers().get(0).positionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			Prefs.put(PrefKey.REFEREE_OVERVIEW_SPLIT, Double.toString(newValue.doubleValue()));
		});

		// icons
		btnAdd.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-add.png")));
		btnEdit.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/edit.png")));
		btnDelete.setGraphic(new ImageView(Resources.loadImage("icons/16x16/actions/list-remove.png")));

	}

	/**
	 * Initializes the controller with things that cannot be done during {@link #initialize()}.
	 *
	 * @param theAppController app controller
	 *
	 * @version 0.12.0
	 * @since 0.10.0
	 */
	public void initController(final AppLayoutController theAppController) {

		appController = theAppController;

		ctlRefList.setItems();

	}

	/**
	 * Shows selected data in detail window.
	 *
	 * @param theDetailData event (null if none is selected)
	 *
	 * @version 0.13.0
	 * @since 0.10.0
	 */
	private void showDetails(final Referee theDetailData) {

		if (theDetailData == null) {

			lblHeading.setText("Details");

			lblName.setText("");
			lblFirstName.setText("");
			lblTrainingLevel.setText("");
			lblClub.setText("");
			lblBirthday.setText("");
			lblLastUpdate.setText("");
			lblNextUpdate.setText("");

			imgReferee.setImage(null);

		} else {

			lblHeading.setText(
					(theDetailData.getDisplayTitle() == null) ?
							null :
							theDetailData.getDisplayTitle().getValue());

			lblName.setText(
					(theDetailData.getName() == null) ?
							null :
							theDetailData.getName().getValue());
			lblFirstName.setText(
					(theDetailData.getFirstName() == null) ?
							null :
							theDetailData.getFirstName().getValue());
			lblTrainingLevel.setText(
					(((RefereeModel) theDetailData).getHighestTrainingLevel() == null) ?
							null :
							((RefereeModel) theDetailData).getHighestTrainingLevel().getType().getDisplayTitle().getValue());
			lblClub.setText(
					(theDetailData.getMember() == null) ?
							null :
							theDetailData.getMember().getDisplayTitle().getValue());
			lblBirthday.setText(
					(theDetailData.getBirthday() == null) ?
							null :
							DateTimeUtils.formatDate((LocalDate) theDetailData.getBirthday().getValue()));
			lblLastUpdate.setText(
					(((RefereeModel) theDetailData).getLastTrainingUpdate() == null) ?
							null :
							DateTimeUtils.formatDate(((RefereeModel) theDetailData).getLastTrainingUpdate().getValue()));
			lblNextUpdate.setText(
					(((RefereeModel) theDetailData).getNextTrainingUpdate() == null) ?
							null :
							DateTimeUtils.formatDate(((RefereeModel) theDetailData).getNextTrainingUpdate().getValue(), "yyyy"));

			try {
				if (theDetailData.existsImageFile(Prefs.get(PrefKey.PATHS_IMAGE))) {
					File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), String.format("%s.jpg", theDetailData.getFileName().getValue())).toFile();
					imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
				} else {
					File fleImage = Paths.get(Prefs.get(PrefKey.PATHS_IMAGE), "missing.jpg").toFile();
					if (fleImage.exists()) {
						imgReferee.setImage(new Image(fleImage.toURI().toURL().toString()));
					} else {
						imgReferee.setImage(null);
					}
				}
			} catch (Exception e) {
				imgReferee.setImage(null);
			}

		}

	}

	/**
	 * Opens edit dialog for new data.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleAdd() {

		RefereeModel newReferee = new RefereeModel();
		if (showEditDialog(newReferee)) {
			((ContentModel) AppModel.getData().getContent()).getObservableReferees().add(newReferee);
			ctlRefList.getRefereesSelectionModel().select(newReferee);
			AppModel.setModified(true);
			appController.setAppTitle();
		}

	}

	/**
	 * Opens edit dialog for editing selected data.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleEdit() {

		RefereeModel editReferee = (RefereeModel) ctlRefList.getRefereesSelectionModel().getSelectedItem();

		if (editReferee != null) {

			if (showEditDialog(editReferee)) {
				showDetails(editReferee);
				AppModel.setModified(true);
				appController.setAppTitle();
			}

		}

	}

	/**
	 * Deletes selected data from list.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	@FXML
	private void handleDelete() {

		Referee refDelete = ctlRefList.getRefereesSelectionModel().getSelectedItem();

		if (refDelete != null) {

			Alert alert = AlertUtils.createAlert(AlertType.CONFIRMATION, appController.getPrimaryStage(),
					"Bestätigung Schiedsrichter löschen",
					MessageFormat.format("Soll ''{0}'' gelöscht werden?", refDelete.getDisplayTitle().get()),
					null);

			alert.showAndWait()
					.filter(response -> response == ButtonType.OK)
					.ifPresent(response -> {
						((ContentModel) AppModel.getData().getContent()).getObservableReferees().remove(refDelete);
						AppModel.setModified(true);
						appController.setAppTitle();
						});

		}

	}

	/**
	 * Opens the referee edit dialog.
	 *
	 * If the user clicks OK, the changes are saved into the provided event object and true is returned.
	 *
	 * @param theReferee the referee to be edited
	 * @return true if the user clicked OK, false otherwise.
	 *
	 * @version 0.10.0
	 * @since 0.10.0
	 */
	private boolean showEditDialog(Referee theReferee) {

		Map.Entry<Pane, FXMLLoader> pneLoad = Resources.loadPane("RefereeEditDialog");
		AnchorPane editDialog = (AnchorPane) pneLoad.getKey();

		// Create the dialog Stage.
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(appController.getPrimaryStage());
		dialogStage.setTitle("Schiedsrichter_in editieren");

		Scene scene = new Scene(editDialog);
		dialogStage.setScene(scene);

		// Set the referee
		RefereeEditDialogController editController = pneLoad.getValue().getController();
		editController.setDialogStage(dialogStage);
		editController.setReferee(theReferee);

		// Show the dialog and wait until the user closes it
		dialogStage.showAndWait();

		return editController.isOkClicked();

	}

}

/* EOF */