package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@ThreadSafe
@Repository
public class UserStore {
    private final CrudRepository crudRepository;

    public Optional<User> add(User user)  {
        Optional<User> rsl = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(user));
            if (user.getId() != 0) {
                rsl = Optional.of(user);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public Optional<User> get(String email, String phone) {
        return crudRepository.optional(
                "from User where email = :email and phone = :phone", User.class,
                Map.of("email", email, "phone", phone)
        );
    }
}
