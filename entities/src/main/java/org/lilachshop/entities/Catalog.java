package org.lilachshop.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany()
    private List<Item> items;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL)
    public Set<ItemPrice> itemPrice = new HashSet<ItemPrice>();

    public int getId() {
        return id;
    }
    public void setItems(List<Item> items){ this.items = items;}
    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
