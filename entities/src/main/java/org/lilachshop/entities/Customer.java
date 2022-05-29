package org.lilachshop.entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
@Entity
@Table(name = "Customers")
public class Customer extends User implements Serializable {

    public Customer(String userName, String userPassword, String name, String address, String phoneNumber, CreditCard card, List<Order> orders, Store store, Account account, Boolean... disabled) {
        super(userName, userPassword);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.card = card;
        card.setCustomer(this);
        this.orders = orders;
        this.store = store;
        this.account = account;
        if (disabled.length > 0)
            this.disabled = disabled[0];
        else
            this.disabled = false;
    }
    @Embedded
    Account account;

    String name;
    String address;
    String phoneNumber;
    Boolean disabled;
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false)
    CreditCard card;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, targetEntity = Order.class)
    List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    public void setCard(CreditCard card) {
        this.card = card;
        card.setCustomer(this);
    }

    public CreditCard getCard() {
        return card;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    protected Customer() {
    }

    public void addOrderToList(Order order) {
        orders.add(order);
    }

}
