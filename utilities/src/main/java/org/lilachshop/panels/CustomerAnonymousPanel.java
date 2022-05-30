package org.lilachshop.panels;

import org.lilachshop.entities.Customer;
import org.lilachshop.requests.SignUpRequest;
import org.lilachshop.requests.CustomerLoginRequest;
import org.lilachshop.requests.DebugRequest;


public class CustomerAnonymousPanel extends Panel{


    public CustomerAnonymousPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void sendCatalogRequestToServer() {
//        sendToServer(new CatalogRequest("get catalog"));
        sendToServer(new DebugRequest("write catalog"));
    }
    public void sendCustomerLoginRequest(String userName, String password){
        sendToServer(new CustomerLoginRequest("customer login request",userName, password));
    }

    public void sendSignUpRequest(Customer customer){
        sendToServer(new SignUpRequest(customer));
    }

}
