package org.lilachshop.entities;


import javax.persistence.*;

@Entity
@Table(name = "DeliveryDetails")
public class DeliveryDetails {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    Order order;

    String DeliveryTime;

    public DeliveryDetails(String deliveryDate, String deliveryTime, String phoneNumber, String receiverName, String address) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
