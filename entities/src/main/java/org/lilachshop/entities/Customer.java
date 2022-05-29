package org.lilachshop.entities;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
@Entity
@Table(name = "Customers")
public class Customer extends User implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    public Customer(String userName, String userPassword, String name, String address, String phoneNumber, Boolean disabled, CreditCard card, List<Order> orders, Store store, Account account) {
        super(userName, userPassword);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.disabled = disabled;
        this.card = card;
        this.orders = orders;
        this.store = store;
        this.account = account;
    }
    @OneToOne
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
