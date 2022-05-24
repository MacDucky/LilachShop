package org.lilachshop.entities;


import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Entity
@Table(name = "DeliveryDetails")
public class DeliveryDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    String DeliveryTime;

    public DeliveryDetails(String deliveryTime, String phoneNumber, String receiverName, String address) {
        DeliveryTime = deliveryTime;
        this.phoneNumber = phoneNumber;
        this.receiverName = receiverName;
        this.address = address;
    }

    public String getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getAddress() {
        return address;
    }



    protected DeliveryDetails() {
    }

    String phoneNumber;
    String receiverName;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
