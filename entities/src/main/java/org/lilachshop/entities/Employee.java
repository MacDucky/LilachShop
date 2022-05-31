package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Entity
@Table(name = "Employees")
public class Employee extends User implements Serializable {
    public Employee(Store store, Role role, String userName, String userPassword) {
        super(userName, userPassword);
        this.store = store;
        this.role = role;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Store store;

    @Enumerated(EnumType.STRING)
    Role role;

//    public Long getId() {
//        return super.getId();
//    }

    protected Employee() {

    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
