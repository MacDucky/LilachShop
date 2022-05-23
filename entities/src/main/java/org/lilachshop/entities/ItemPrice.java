package org.lilachshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "ItemPriceDict")
public class ItemPrice {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    protected ItemPrice() {
    }

    public ItemPrice(Item item, Catalog catalog, double price) {
        this.item = item;
        this.catalog = catalog;
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name="id")
    public Item item;
    @ManyToOne
    @JoinColumn(name="id")
    public Catalog catalog;

    @Column(name = "price")
    private double price;


}
