package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    public int getId() {
        return id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        for (Item item : items) {
            item.setCatalog(this);
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder s_repr = new StringBuilder("Catalog ID: " + id);
        for (Item item : items) {
            s_repr.append("\n").append(item);
        }
        return s_repr.toString();
    }
}
