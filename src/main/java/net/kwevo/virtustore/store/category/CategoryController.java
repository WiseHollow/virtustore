package net.kwevo.virtustore.store.category;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConstructorBinding
@RequestMapping("/api/core/category")
public record CategoryController(CategoryService categoryService) {

    @GetMapping
    public List<CategoryModel> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryModel get(@PathVariable Long categoryId) {
        return categoryService.get(categoryId);
    }

    @PostMapping
    public CategoryModel create(@RequestBody CategoryModel categoryModel) {
        return categoryService.create(categoryModel);
    }

    @PutMapping
    public CategoryModel update(@RequestBody CategoryModel categoryModel) {
        return categoryService.update(categoryModel);
    }

    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }

    @PostMapping("/{categoryId}/{itemId}")
    public CategoryModel addItem(@PathVariable Long categoryId, @PathVariable Long itemId) {
        return categoryService.addItem(categoryId, itemId);
    }

    @DeleteMapping("/{categoryId}/{itemId}")
    public CategoryModel removeItem(@PathVariable Long categoryId, @PathVariable Long itemId) {
        return categoryService.removeItem(categoryId, itemId);
    }
}
