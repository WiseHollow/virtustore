package net.kwevo.virtustore.store.category;

import java.util.List;
import net.kwevo.virtustore.store.item.Item;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> contents;

    Category() {

    }

    Category(Long id, String name, List<Item> contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
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

    public List<Item> getContents() {
        return contents;
    }

    public void setContents(List<Item> contents) {
        this.contents = contents;
    }
}
