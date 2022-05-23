package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Complaint")
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String creationDate;
    String endOfHandleDate;
    String status;
    String content;
    String reply;

    @OneToOne
    Order order;

    public Complaint() {}

    public int getId() {
        return id;
    }

    public Complaint(String endOfHandleDate, String status, String content, String creationDate, String reply) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
