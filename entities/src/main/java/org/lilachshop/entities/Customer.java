package org.lilachshop.entities;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends User {

    protected Customer() {
    }

    public Customer(String username, String password, String firstName, String lastName,
                    AccountType accountType, Address address, CreditCard creditCard, Store store, Boolean... isDisabled) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.address = address;
        this.creditCard = creditCard;
        this.store = store;
        if (isDisabled.length < 1) {
            this.isDisabled = false;
        } else
            this.isDisabled = isDisabled[0];
    }

    @Column(name = "first_name")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "last_name")
    @Basic(optional = false)
    private String lastName;

    @Column(nullable = false)
    @Basic(optional = false)
    private Boolean isDisabled;

    @Embedded
    @Basic(optional = false)
    @AttributeOverride(name = "streetName", column = @Column(name = "street_name"))
    Address address;

    @Embedded
    @Column(nullable = false)
    @Basic(optional = false)
    private CreditCard creditCard;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    @Basic(optional = false)
    AccountType accountType;

    @ManyToOne
    Store store;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
