package ru.job4j.todo.store;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import ru.job4j.todo.model.Item;

@ThreadSafe
@Repository
public class ItemStore {

    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        item.setCreated(new Date());
        item.setDone(false);
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public void update(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        item.setId(id);
        item.setCreated(new Date());
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item", Item.class).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item  where name = :paramName", Item.class)
                .setParameter("paramName", key).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> condition(boolean done) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item  where done = :paramDone", Item.class)
                .setParameter("paramDone", done).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void completedId(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("update Item i set i.done = true where i.id = :id").
                setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


}