package net.kwevo.virtustore.store.item;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConstructorBinding
@RequestMapping("/api/core/item")
public record ItemController(ItemService itemService) {

    @GetMapping
    public List<ItemModel> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{itemId}")
    public ItemModel get(@PathVariable long itemId) {
        return itemService.get(itemId);
    }

    @PostMapping
    public ItemModel create(@RequestBody ItemModel itemModel) {
        return itemService.save(itemModel);
    }

    @PutMapping
    public ItemModel update(@RequestBody ItemModel itemModel) {
        return itemService.update(itemModel);
    }

    @DeleteMapping("/{itemId}")
    public void delete(@PathVariable long itemId) {
        itemService.delete(itemId);
    }
}
