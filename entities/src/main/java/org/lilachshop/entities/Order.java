package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Order(String creationDate, String greetingCard, List<Item> items, int totalPrice, int amountOfProducts, DeliveryDetails deliveryDetails, PickUpDetails pickUpDetails, Complaint complaint, Customer customer) {
        this.creationDate = creationDate;
        this.greetingCard = greetingCard;
        this.items = items;
        this.totalPrice = totalPrice;
        this.amountOfProducts = amountOfProducts;
        this.deliveryDetails = deliveryDetails;
        this.pickUpDetails = pickUpDetails;
        this.complaint = complaint;
        this.customer = customer;
    }

    String creationDate;
    String greetingCard;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "order")
    List<Item> items = new LinkedList<>();

    int totalPrice;

    int amountOfProducts;

    @OneToOne(cascade = CascadeType.ALL)
    DeliveryDetails deliveryDetails;

    @OneToOne(cascade = CascadeType.ALL)
    PickUpDetails pickUpDetails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    Complaint complaint;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    Customer customer;

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

    protected Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
