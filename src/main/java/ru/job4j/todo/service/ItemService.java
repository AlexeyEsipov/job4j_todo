package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.ItemStore;

import java.util.Collection;

@ThreadSafe
@Service
public class ItemService {
    private final ItemStore store;

    public ItemService(ItemStore store) {
        this.store = store;
    }

    public Collection<Item> findAll(User user) {
        return store.findAll(user);
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

    public Collection<Item> completed(User user) {
        return store.condition(user, true);
    }

    public Collection<Item> notCompleted(User user) {
        return store.condition(user, false);
    }

    public void completedId(int id) {
        store.completedId(id);
    }
}