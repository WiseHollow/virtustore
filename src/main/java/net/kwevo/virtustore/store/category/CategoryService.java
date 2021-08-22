package net.kwevo.virtustore.store.category;

import net.kwevo.virtustore.exceptions.ConflictException;
import net.kwevo.virtustore.exceptions.InvalidStoreCategoryException;
import net.kwevo.virtustore.exceptions.NotFoundException;
import net.kwevo.virtustore.store.item.Item;
import net.kwevo.virtustore.store.item.ItemRepository;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConstructorBinding
public record CategoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
    public List<CategoryModel> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryModel::new)
                .collect(Collectors.toList());
    }

    public CategoryModel get(Long categoryId) {
        Category entity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Could not find 'Category' based on ID '" + categoryId + "'."));
        return new CategoryModel(entity);
    }

    public CategoryModel create(CategoryModel categoryModel) {
        categoryModel.setId(null);
        validate(categoryModel);

        Category savedCategory = categoryRepository.save(categoryModel.toEntity());
        return new CategoryModel(savedCategory);
    }

    public CategoryModel update(CategoryModel categoryModel) {
        if (!categoryRepository.existsById(categoryModel.getId())) {
            throw new NotFoundException("Could not update 'Category' based on ID '" + categoryModel.getId() + "'.");
        }

        validate(categoryModel);

        Category savedCategory = categoryRepository.save(categoryModel.toEntity());
        return new CategoryModel(savedCategory);
    }

    public void delete(Long categoryId) {
        Optional<Category> categoryToDelete = categoryRepository.findById(categoryId);
        if (categoryToDelete.isEmpty()) {
            throw new NotFoundException("Could not delete 'Category' based on ID '" + categoryId + "'.");
        }

        categoryRepository.delete(categoryToDelete.get());
    }

    public CategoryModel addItem(Long categoryId, Long itemId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Could not add Item to 'Category' based on Category ID '" + categoryId + "'.");
        }

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Could not add Item to 'Category' based on Item ID '" + itemId + "'.");
        }


        Category category = optionalCategory.get();
        Item item = optionalItem.get();
        if (category.getContents().contains(item)) {
            throw new ConflictException("Could not add Item to 'Category' based on already existing link with Item ID '" + itemId + "'.");
        }

        category.getContents().add(item);
        category = categoryRepository.save(category);
        return new CategoryModel(category);
    }

    public CategoryModel removeItem(Long categoryId, Long itemId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("Could not remove Item from 'Category' based on Category ID '" + categoryId + "'.");
        }

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Could not remove Item from 'Category' based on Item ID '" + itemId + "'.");
        }

        Category category = optionalCategory.get();
        Item item = optionalItem.get();
        if (!category.getContents().contains(item)) {
            throw new NotFoundException("Could not remove Item from 'Category' based on missing link with Item ID '" + itemId + "'.");
        }

        category.getContents().remove(item);
        category = categoryRepository.save(category);
        return new CategoryModel(category);
    }

    private void validate(CategoryModel categoryModel) {
        if (categoryModel.getName() == null) {
            throw new InvalidStoreCategoryException("Category must have a name.");
        }
    }
}
