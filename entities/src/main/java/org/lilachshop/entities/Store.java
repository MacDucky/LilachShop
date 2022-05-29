package org.lilachshop.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name = "Stores")
public class Store implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Store() {
    }

    public Long getId() {
        return id;
    }

    public Store(String address, String storeName, Catalog catalog, List<Complaint> complaints, List<Order> orders) {
        this.address = address;
        this.storeName = storeName;
        this.catalog = catalog;
        this.complaints = complaints;
        this.orders = orders;
    }

    String address;

    String storeName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Catalog catalog;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // if store is removed, so will its complaints.
    @JoinColumn(name = "store_id")
    List<Complaint> complaints = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", targetEntity = Order.class)
    List<Order> orders;

    public String getAddress() {
        return address;
    }

    public String getStoreName() {
        return storeName;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(Complaint complaint) {
        this.complaints.add(complaint);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

}
