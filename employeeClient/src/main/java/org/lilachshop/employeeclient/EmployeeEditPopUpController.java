package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EmployeeEditPopUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField employeeFirstNameTF;

    @FXML
    private TextField employeeLastNameTF;

    @FXML
    private TextField employeeTF;

    @FXML
    private ChoiceBox<?> roleChoiceBox;

    @FXML
    private ChoiceBox<?> storeChoiceBox;

    @FXML
    void initialize() {
        assert employeeFirstNameTF != null : "fx:id=\"employeeFirstNameTF\" was not injected: check your FXML file 'EditEmployeePopUp.fxml'.";
        assert employeeLastNameTF != null : "fx:id=\"employeeLastNameTF\" was not injected: check your FXML file 'EditEmployeePopUp.fxml'.";
        assert employeeTF != null : "fx:id=\"employeeTF\" was not injected: check your FXML file 'EditEmployeePopUp.fxml'.";
        assert roleChoiceBox != null : "fx:id=\"roleChoiceBox\" was not injected: check your FXML file 'EditEmployeePopUp.fxml'.";
        assert storeChoiceBox != null : "fx:id=\"storeChoiceBox\" was not injected: check your FXML file 'EditEmployeePopUp.fxml'.";

    }

}

