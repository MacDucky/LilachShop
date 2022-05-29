package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Items")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    private String name;

    private int price;
    private String image;

    @ManyToOne(optional = false)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item ID:" + id + ", item name: " + name + ", price: " + price + ", has_image: " +
                (Boolean) (image.length() > 0) + ", Catalog ID: " + catalog.getId();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name, int price, String image) {
        super();
        this.name = name;
        this.price = price;
        this.image = image;

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
