package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.lilachshop.entities.Employee;
import org.lilachshop.entities.Role;
import org.lilachshop.entities.Store;

public class EmployeeTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<String, String> employeeFirstName;

    @FXML
    private TableColumn<Long,Long> employeeID;

    @FXML
    private TableColumn<String, String>employeeLastName;

    @FXML
    private TableColumn<Role, Role> employeeRole;

    @FXML
    private TableColumn<Store, String> employeeStore;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private Button updateBtn;

    @FXML
    void initialize() {
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeFirstName != null : "fx:id=\"employeeFirstName\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeID != null : "fx:id=\"employeeID\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeLastName != null : "fx:id=\"employeeLastName\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeRole != null : "fx:id=\"employeeRole\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeStore != null : "fx:id=\"employeeStore\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeTable != null : "fx:id=\"employeeTable\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";

    }

}
