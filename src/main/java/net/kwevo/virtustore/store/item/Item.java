package net.kwevo.virtustore.store.item;

import net.kwevo.virtustore.store.category.Category;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @Lob
    private Byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    Item() {
    }

    Item(Long id, String name, float price, Byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
