package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User implements Serializable {

    protected User() {
    }

    public User( String username, String password) {
//        this.userID = userID;
        this.username = username;
        this.password = password;
        accountCreationDate = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    protected int userID;

    @Column(nullable = false, unique = true)
    @Basic(optional = false)
    String username;

    @Column(nullable = false)
    @Basic(optional = false)
    String password;

    @Column(name = "creation_date", nullable = false)
    @Basic(optional = false)
    LocalDate accountCreationDate;

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }
}
