package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Complaints")
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String creationDate;
    String endOfHandleDate;
    String status;
    String complaintNumber;
    String content;
    String reply;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    protected Complaint() {
    }

    public Long getId() {
        return id;
    }

    public Complaint(String endOfHandleDate, String status, String complaintNumber, String content) {
        this.endOfHandleDate = endOfHandleDate;
        this.status = status;
        this.content = content;
        this.creationDate = creationDate;
        this.reply = reply;
    }

    public String getCreationDate() {
        return endOfHandleDate;
    }

    public void setCreationDate(String creationDate) {
        this.endOfHandleDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
