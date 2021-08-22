package net.kwevo.virtustore.store.item;

import net.kwevo.virtustore.exceptions.InvalidStoreItemException;
import net.kwevo.virtustore.exceptions.NotFoundException;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConstructorBinding
public record ItemService(ItemRepository itemRepository) {
    public List<ItemModel> getAll() {
        return itemRepository.findAll()
                .stream()
                .map(ItemModel::new)
                .collect(Collectors.toList());
    }

    public ItemModel get(long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new NotFoundException("Could not find 'Item' based on ID '" + itemId + "'.")
        );

        return new ItemModel(item);
    }

    public ItemModel save(ItemModel itemModel) {
        itemModel.setId(null);
        validateItem(itemModel);

        Item savedItem = itemRepository.save(itemModel.toEntity());
        return new ItemModel(savedItem);
    }

    public ItemModel update(ItemModel itemModel) {
        if (!itemRepository.existsById(itemModel.getId())) {
            throw new NotFoundException("Could not update 'Item' based on ID '" + itemModel.getId() + "'.");
        }

        validateItem(itemModel);
        Item item = itemRepository.save(itemModel.toEntity());
        return new ItemModel(item);
    }

    public void delete(long itemId) {
        Optional<Item> itemToDelete = itemRepository.findById(itemId);
        if (itemToDelete.isEmpty()) {
            throw new NotFoundException("Could not delete 'Item' based on ID '" + itemId + "'.");
        }

        itemRepository.delete(itemToDelete.get());
    }

    private void validateItem(ItemModel itemModel) {
        if (itemModel.getName() == null) {
            throw new InvalidStoreItemException("Item requires a name.");
        } else if (itemModel.getPrice() < 0) {
            throw new InvalidStoreItemException("Item price must be at least 0.");
        }
    }
}
