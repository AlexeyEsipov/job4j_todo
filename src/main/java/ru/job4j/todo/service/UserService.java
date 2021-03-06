package ru.job4j.todo.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import java.util.Optional;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.UserStore;


@ThreadSafe
@Service
public class UserService {
    private final UserStore store;

    public UserService(UserStore store) {
        this.store = store;
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public Optional<User> get(String email, String phone) {
        return store.get(email, phone);
    }
}
