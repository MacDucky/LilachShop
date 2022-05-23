package org.lilachshop.entities;


import javax.persistence.Embeddable;
import java.io.Serializable;

public enum AccountType implements Serializable {
    STORE,
    CHAIN,
    ANNUAL;
}
