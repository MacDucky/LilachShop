package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
@Transactional
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setGreetingCard(String greetingCard) {
        this.greetingCard = greetingCard;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public void setPickUpDetails(PickUpDetails pickUpDetails) {
        this.pickUpDetails = pickUpDetails;
    }

    public Order(List<Item> items, int totalPrice, int amountOfProducts, Customer customer)
    {
        this.items = items;
        this.totalPrice = totalPrice;
        this.amountOfProducts = amountOfProducts;
        this.customer = customer;
    }
    public Order(LocalDate creationDate, String greetingCard, List<Item> items, int totalPrice, int amountOfProducts, DeliveryDetails deliveryDetails, PickUpDetails pickUpDetails, Complaint complaint, Customer customer) {
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

    public List<Item> getItems() {
        return items;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    @ManyToOne
    Store store;
    String greetingCard;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Item> items;
    int totalPrice;
    int amountOfProducts;
    @OneToOne(cascade = CascadeType.ALL)
    DeliveryDetails deliveryDetails;
    @OneToOne(cascade = CascadeType.ALL)
    PickUpDetails pickUpDetails;

    @OneToOne(cascade = CascadeType.ALL)
    Complaint complaint;

    @ManyToOne
    Customer customer;

    LocalDate creationDate;

    protected Order() {

    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
