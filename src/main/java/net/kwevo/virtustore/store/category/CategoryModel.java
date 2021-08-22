package net.kwevo.virtustore.store.category;

import net.kwevo.virtustore.store.item.Item;
import net.kwevo.virtustore.store.item.ItemModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryModel {
    private Long id;
    private String name;
    private List<ItemModel> contents;

    public CategoryModel() {

    }

    public CategoryModel(Long id, String name, List<Item> contents) {
        this.id = id;
        this.name = name;
        this.contents = contents.stream()
                .map(ItemModel::new)
                .collect(Collectors.toList());
    }

    public CategoryModel(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.contents = entity.getContents().stream()
                .map(ItemModel::new)
                .collect(Collectors.toList());
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

    public List<ItemModel> getContents() {
        return contents;
    }

    public void setContents(List<ItemModel> contents) {
        this.contents = contents;
    }

    public Category toEntity() {
        List<Item> entityContents = contents.stream()
                .map(ItemModel::toEntity)
                .collect(Collectors.toList());
        return new Category(id, name, entityContents);
    }
}
