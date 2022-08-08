package ru.job4j.todo.store;

import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

import ru.job4j.todo.model.Category;

@ThreadSafe
@Repository
public class CategoryStore {
    private final SessionFactory sf;

    public CategoryStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Category add(Category category) {
        return this.tx(
                session ->  {
                    session.save(category);
                    return category;
                }
        );
    }

    public List<Category> findAll() {
        return this.tx(
                session -> session.createQuery("from Category", Category.class).list()
        );
    }

    public Category findById(int id) {
        return this.tx(
                session -> session.get(Category.class, id)
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