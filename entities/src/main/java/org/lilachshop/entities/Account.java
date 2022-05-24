package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;

@Transactional
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String creationDate;
    AccountType accountType;

    public Long getId() {
        return id;
    }


    protected Account() {}

    public AccountType getAccountType() {
        return accountType;
    }


    public Account(String creationDate, AccountType accountType) {
        this.creationDate = creationDate;
        this.accountType = accountType;
    }
}
