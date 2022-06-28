package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.store.ItemStore;

@ThreadSafe
@Service
public class ItemService {
    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public Collection<Item> findAll() {
        return store.findAll();
    }

    public Item add(Item item) {
        return store.add(item);
    }

    public void update(int id, Item item) {
        store.update(id, item);
    }

    public Item findById(int id) {
        return store.findById(id);
    }

    public void delete(int id) {
        store.delete(id);
    }

    public List<Item> findByName(String key) {
        return store.findByName(key);
    }

    public Collection<Item> completed() {
        return store.condition(true);
    }

    public Collection<Item> notCompleted() {
        return store.condition(false);
    }

    public void completedId(int id) {
        store.completedId(id);
    }
}