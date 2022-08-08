package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.CategoryStore;

import java.util.Collection;

@ThreadSafe
@Service
public class CategoryService {
    private final CategoryStore store;

    public CategoryService(CategoryStore store) {
        this.store = store;
    }

    public Category add(Category category) {
        return store.add(category);
    }

    public Collection<Category> findAll() {
        return store.findAll();
    }

    public Category findById(Integer id) {
        return store.findById(id);
    }
}

