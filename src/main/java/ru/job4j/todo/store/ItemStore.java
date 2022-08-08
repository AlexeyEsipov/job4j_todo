package ru.job4j.todo.store;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

@ThreadSafe
@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item add(Item item) {
        return this.tx(
            session -> {
                item.setCreated(new Date(System.currentTimeMillis()));
                item.setDone(false);
                session.save(item);
                return item;
            }
        );
    }

    public void update(int id, Item item) {
        this.tx(
            session -> {
                item.setId(id);
                item.setCreated(new Date(System.currentTimeMillis()));
                session.update(item);
                return session;
            }
        );
    }

    public void delete(int id) {
        this.tx(
            session -> {
                Item item = new Item();
                item.setId(id);
                session.delete(item);
                return session;
            }
        );
    }

    public List<Item> findAll(User user) {
        if (user != null && user.getUserId() != 0) {
            return this.tx(
                session ->
                    session.createQuery(
                            "select distinct item from Item  item join fetch item.categories "
                               + "where item.user = :userId", Item.class)
                    .setParameter("userId", user).list()
            );
        } else {
            return List.of();
        }
    }

    public Item findById(int id) {
        return this.tx(
            session ->
                session.createQuery(
                        "select distinct item from Item  item join fetch item.categories"
                           + " where item.id = :paramId", Item.class)
                .setParameter("paramId", id).uniqueResult()
        );
    }

    public List<Item> condition(User user, boolean done) {
        return this.tx(
            session ->
                session.createQuery(
                        "select distinct item from Item item join fetch item.categories "
                           + "where item.done = :paramDone and item.user = :userId", Item.class)
                .setParameter("paramDone", done)
                .setParameter("userId", user).list()
        );
    }

    public void completedId(int id) {
        this.tx(
            session -> session.createQuery("update Item i set i.done = true where i.id = :id")
                    .setParameter("id", id).executeUpdate()
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}