package org.lilachshop.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "stores")
public class Store implements Serializable {

    protected Store() {
    }

    public Store(String storeName, Address address) {
        this.storeName = storeName;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int storeID;

    @Column(nullable = false)
    @Basic(optional = false)
    String storeName;

    @Embedded
    @Basic(optional = false)
    @AttributeOverride(name = "streetName", column = @Column(name = "street_name"))
    Address address;

    @OneToMany(mappedBy = "userID")
    @Column(name = "store_employees")
    private List<Employee> employees;

    @OneToMany(mappedBy = "store")
    private List<Customer> customers;

    public int getStoreID() {
        return storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
