package org.lilachshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "PickUpDetails")
public class PickUpDetails {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    Order order;

    String PickUptime;

    public String getPickUptime() {
        return PickUptime;
    }

    public void setPickUptime(String pickUptime) {
        PickUptime = pickUptime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
