package org.lilachshop.panels;

import org.lilachshop.panels.Panel;
import org.lilachshop.requests.EmployeeLoginRequest;
import org.lilachshop.requests.Request;

public class EmployeeAnonymousPanel extends Panel {
    public EmployeeAnonymousPanel(String host, int port, Object controller) {
        super(host, port, controller);
    }
    public void sendLoginRequest(String userName, String password){
        sendToServer(new EmployeeLoginRequest("client login", userName, password));
    }

}
