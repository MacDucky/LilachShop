package org.lilachshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    String creationDate;
    String greetingCard;
    @OneToMany
    List<Item> items;
    int totalPrice;
    int amountOfProducts;
    @OneToOne
    DeliveryDetails deliveryDetails;
    @OneToOne
    PickUpDetails pickUpDetails;

    @OneToOne
    Complaint complaint;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
