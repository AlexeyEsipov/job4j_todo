package ru.job4j.todo.store;
import net.jcip.annotations.ThreadSafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.function.Function;

import ru.job4j.todo.model.User;

@ThreadSafe
@Repository
public class UserStore {
    private final SessionFactory sf;

    public UserStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Optional<User> add(User user)  {
        Optional<User> rsl = Optional.empty();
        try {
            User user1 = this.tx(
                    session -> {
                        session.save(user);
                        return user;
                    }
            );
            if (user1.getUserId() != 0) {
                rsl = Optional.of(user1);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Optional<User> get(String email, String phone) {
        return this.tx(
            session -> {
                Query<User> query = session.createQuery(
                        "from User where email = :email and phone = :phone", User.class);
                query.setParameter("email", email);
                query.setParameter("phone", phone);
                return query.uniqueResultOptional();
            }
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
