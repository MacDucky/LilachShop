package org.lilachshop.panels;

import org.jetbrains.annotations.Nullable;

public class OperationsPanelFactory {
    private OperationsPanelFactory() {
    }


    @Nullable
    public static Panel createPanel(int panel_num, Object controller) {    // for now int, later going to be enum!
        switch (panel_num) {
            case 1 -> {
                return new ExamplePanel("localhost", 3000, controller);
            }
            case 2 -> {
                return new CustomerAnonymousPanel("localhost", 3000, controller);
            }
            case 3 -> {
                return new RegisteredCustomerPanel("localhost", 3000, controller);
            }
            case 4 -> {
                return new CustomerServicePanel("localhost", 3000, controller);
            }
            case 5 -> {
                return new EmployeeAnonymousPanel("localhost", 3000, controller);
            }
            case 6 -> {
                return new SysAdminPanel("localhost", 3000, controller);
            }
            default -> {
                System.out.println("No panel found.");
                return null;
            }
        }
    }
}
