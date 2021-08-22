package net.kwevo.virtustore.store.item;

public class ItemModel {
    private Long id;

    private String name;

    private float price;

    private Byte[] image;

    public ItemModel() {
    }

    public ItemModel(Long id, String name, float price, Byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public ItemModel(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();;
        this.image = item.getImage();
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

    public Item toEntity() {
        return new Item(id, name, price, image);
    }
}
