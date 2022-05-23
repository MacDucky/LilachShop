package org.lilachshop.entities;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    @Basic(optional = false)
    String city;
    @Basic(optional = false)
    String streetName;

    protected Address() {
    }

    public Address(String city, String streetName) {
        this.city = city;
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
