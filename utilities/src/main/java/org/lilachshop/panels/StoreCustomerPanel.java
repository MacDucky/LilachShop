package org.lilachshop.panels;


import org.lilachshop.commonUtils.Socket;
import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.Customer;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.User;
import org.lilachshop.requests.*;

public class StoreCustomerPanel extends RegisteredCustomerPanel {
    public StoreCustomerPanel(Socket socket, Object controller) {
        super(socket, controller);
    }

    public void sendComplaintToServer(Complaint complaint, Order order) {
        sendToServer(new UserComplaintRequest("post new complaint", complaint, order));
    }

    public void sendGetCatalogRequestToServer(long id_store) {
        sendToServer(new CatalogRequest("get catalog", id_store));
    }

    public void sendGetAllOrdersToServer(long customerId) {
        sendToServer(new OrderRequest("get all clients orders", customerId));
    }

    public void sendNewOrderCreationToServer(Order myOrder) {
        sendToServer(new OrderRequest("create new order", myOrder));
    }
    public void sendSignOutRequestToServer(User myCustomer) {
        sendToServer(new SignOutRequest(SignOutRequest.Messages.SIGN_OUT, myCustomer));
    }


    public void getAllStores() {
        sendToServer((new StoreRequest("get all stores")));
    }
}
