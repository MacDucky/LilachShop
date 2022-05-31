/**
 * Sample Skeleton for 'test.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.lilachshop.entities.Customer;
import org.lilachshop.entities.Order;

public class HistoryController {
    List<myOrderItem> myFlowers = new ArrayList<myOrderItem>();
    CatalogController controllerInstance;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="layout"
    private VBox itemLayout; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader
    private List<Order> orders;

    private Scene catalogScene;


    @FXML
    void returnToCatalog(MouseEvent event) {

        Stage stage = App.getStage();
        stage.setScene(catalogScene);
        stage.show();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("historyItem.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            itemLayout.getChildren().add(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setData(CatalogController catalogController,Scene catalogScene) {
        this.controllerInstance = catalogController;
        this.orders = controllerInstance.customer.getOrders();
        this.catalogScene = catalogScene;
    }
}
