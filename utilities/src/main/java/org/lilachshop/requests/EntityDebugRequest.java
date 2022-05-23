package org.lilachshop.requests;

import org.lilachshop.entities.Customer;

public class EntityDebugRequest extends Request {
    private final Customer customer;

    public EntityDebugRequest(String request, Customer customer) {
        super(request);
        this.customer = customer;
    }

    @Override
    public String getRequest() {
        return super.getRequest();
    }

    public Customer getCustomer() {
        return customer;
    }
}
