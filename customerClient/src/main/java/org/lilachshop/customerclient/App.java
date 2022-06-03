package org.lilachshop.customerclient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.events.ItemsEvent;
import org.lilachshop.commonUtils.Utilities;
import org.lilachshop.entities.*;
import org.lilachshop.events.OrderEvent;
import org.lilachshop.panels.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Panel panel;
    private static App controller;
    private static final int shipPrice = 15;
    private static Customer myCustomer = null;

    public static Panel getPanel() {
        return panel;
    }

    public static void setPanel(Panel panel) {
        App.panel = panel;
    }

    public static Customer getMyCustomer() {
        return myCustomer;
    }

    public static void setMyCustomer(Customer myCustomer) {
        App.myCustomer = myCustomer;
    }

    public static List<myOrderItem> getMyFlowers() {
        return myFlowers;
    }

    public static void setMyFlowers(List<myOrderItem> myFlowers) {
        App.myFlowers = myFlowers;
    }

    private static Scene scene;
    private static Stage stage;

    public static List<Order> getMyOrders() {
        return myOrders;
    }

    public static void setMyOrders(List<Order> myOrders) {
        App.myOrders = myOrders;
    }

    private static List<myOrderItem> myFlowers;
    private static List<Order> myOrders;
    private static long storeId;

    public static Stage getStage() {
        return stage;
    }

    public static void CreatePanel() {
        AccountType userAccountType = myCustomer.getAccount().getAccountType();
        if (userAccountType.equals(AccountType.CHAIN_ACCOUNT)) {
            panel = OperationsPanelFactory.createPanel(PanelEnum.CHAIN_CUSTOMER, controller);
            storeId = myCustomer.getStore().getId();
            // todo: implement enable store combo box!
        } else if (userAccountType.equals(AccountType.STORE_ACCOUNT)) {
            panel = OperationsPanelFactory.createPanel(PanelEnum.STORE_CUSTOMER, controller);
            storeId = myCustomer.getStore().getId();
        } else {
            panel = OperationsPanelFactory.createPanel(PanelEnum.ANNUAL_CUSTOMER, controller);
            storeId = myCustomer.getStore().getId();
        }
        getCustomerCatalog();
    }


    public static void getCustomerCatalog() {
        ((StoreCustomerPanel) panel).sendGetCatalogRequestToServer(storeId);
    }

    public static App getApp() {
        return controller;
    }

    public static void setApp(App app) {
        App.controller = app;
    }

    public static int getShipPrice() {
        return shipPrice;
    }

    @Override
    public void init() throws Exception {
        System.out.println("Starting customer application...");
        panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS, this);
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        ((CustomerAnonymousPanel) panel).sendGetGeneralCatalogRequestToServer();

    }

    @Subscribe
    public void handleMessageReceivedFromClient(ItemsEvent msg) {
        Platform.runLater(() -> {
            try {
                List<Item> flowerList = msg.getItems();
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
                Parent root = fxmlLoader.load();
                CatalogController controller = fxmlLoader.getController();
                controller.showInfo(flowerList, this);
                stage.setScene(new Scene(root));
                stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::onCloseWindowEvent);
                stage.show();
            } catch (IOException e) {

            }
        });
    }

    @Subscribe
    public void handleReceivedOrderList(OrderEvent msg) {
            Platform.runLater(() -> {
                try {
                    Stage stage = App.getStage();
                    App.setMyOrders(msg.getOrders());
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("test.fxml"));
                    Parent root = fxmlLoader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {

                }
            });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void onCloseWindowEvent(WindowEvent event) {
        System.out.println("Graceful termination, goodbye ;)");
        System.exit(0);
    }
}
