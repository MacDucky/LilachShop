package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Entity
@Table(name = "PickUpDetails")
public class PickUpDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    String PickUptime;

    public PickUpDetails(String pickUptime) {
        PickUptime = pickUptime;
    }

    protected PickUpDetails() {

    }

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
