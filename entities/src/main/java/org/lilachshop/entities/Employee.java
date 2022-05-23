package org.lilachshop.entities;


import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends User {

    protected Employee() {
    }

    public Employee(String username, String password, String firstName, String lastName, EmployeeRole employeeRole, Store store) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeRole = employeeRole;
        this.store = store;
    }

    @Column(name = "first_name")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "last_name")
    @Basic(optional = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")//, nullable = false)
    @Basic(optional = false)
    EmployeeRole employeeRole;

    @ManyToOne
//    @JoinColumn(name = "userID")
    Store store;

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

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
