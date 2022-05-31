package org.lilachshop.requests;

public class StoreRequest extends Request {
    private int storeID;

    public StoreRequest(String request) {
        super(request);
    }

    public StoreRequest(String request, int storeID) {
        super(request);
        this.storeID = storeID;
    }

    public int getStoreID() {
        return storeID;
    }
}
