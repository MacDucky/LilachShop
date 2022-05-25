package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Entity
@Table(name = "CreditCard")
public class CreditCard {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String number;
    String expDate;
    String threeDigits;

    @OneToOne
    Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected CreditCard() {
    }

    public String getNumber() {
        return number;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getThreeDigits() {
        return threeDigits;
    }

    public CreditCard(String number, String expDate, String threeDigits) {
        this.number = number;
        this.expDate = expDate;
        this.threeDigits = threeDigits;
    }
    public void setCustomer(Customer customer){this.customer = customer;}
}
