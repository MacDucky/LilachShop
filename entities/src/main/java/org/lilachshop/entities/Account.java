package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class Account implements Serializable {

    @Column(updatable = false)
    LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    protected Account() {
    }

    public Account(AccountType accountType) {
        this.creationDate = LocalDate.now();
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
