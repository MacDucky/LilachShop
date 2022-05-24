package org.lilachshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class User {

    private Long id;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }
    String userName;
    String userPassword;
}
