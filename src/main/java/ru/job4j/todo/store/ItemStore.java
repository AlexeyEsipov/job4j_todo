package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ThreadSafe
@Repository
public class ItemStore {
    private final CrudRepository crudRepository;
    public Item add(Item item) {
        crudRepository.run(session -> {
            item.setDone(false);
            item.setCreated(new Date(System.currentTimeMillis()));
            session.save(item);
        });
        return item;
    }

    public void update(int id, Item item) {
        crudRepository.run(
            session -> {
                item.setId(id);
                item.setCreated(new Date(System.currentTimeMillis()));
                session.merge(item);
            }
        );
    }

    public void delete(int id) {
        crudRepository.run(
                "delete from Item where id = :fId",
                Map.of("fId", id)
        );
    }

    public List<Item> findAll(User user) {
        if (user != null && user.getId() != 0) {
            return crudRepository.query(
                    "select distinct item from Item  item join fetch item.categories "
                    + "where item.user = :userId", Item.class,
                    Map.of("userId", user));
        } else {
            return List.of();
        }
    }

    public Item findById(int id) {
        return crudRepository.optional("select distinct item from Item  item join fetch item.categories"
                + " where item.id = :paramId", Item.class,
                Map.of("paramId", id)).orElse(new Item());
    }

    public void completedId(int id) {
        Item it = findById(id);
        it.setDone(true);
        crudRepository.run(session -> session.merge(it));
    }

    public List<Item> condition(User user, boolean done) {
        return crudRepository.query(
                "select distinct item from Item item join fetch item.categories "
                        + "where item.done = :paramDone and item.user = :userId", Item.class,
                Map.of("paramDone", done, "userId", user));
    }
}