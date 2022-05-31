/**
 * Sample Skeleton for 'OrderStage1.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.lilachshop.entities.Order;

public class OrderStage1Controller {

    Order myOrder;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cardField"
    private TextArea cardField; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="next"
    private Button next; // Value injected by FXMLLoader
    private Scene catalogScene;
    private CatalogController catalogController;

    @FXML
    void gotoSignUpOrPersonalArea(MouseEvent event) {

    }

    @FXML
    void returnToCatalog(MouseEvent event) {
        Stage stage = App.getStage();
        stage.setScene(catalogScene);
        stage.show();
    }

    @FXML
    void gotoNext(ActionEvent event)
    {
            Stage stage = App.getStage();
            try {
                if (cardField.getText().equals(""))
                    myOrder.setGreetingCard(null);
                else
                    myOrder.setGreetingCard(cardField.getText());
                FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage2.fxml"));
                Parent root = fxmlLoader1.load();
                OrderStage2Controller orderStage2Controller = fxmlLoader1.getController();
                orderStage2Controller.showInfo(myOrder,catalogScene,catalogController);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {


    }

    public void showInfo(Order order, Scene catalogScene, CatalogController catalogController) {
        myOrder = order;
        this.catalogScene = catalogScene;
        this.catalogController = catalogController;
    }
}
