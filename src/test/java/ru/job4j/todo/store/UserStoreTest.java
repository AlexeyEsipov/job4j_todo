package ru.job4j.todo.store;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.todo.model.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class UserStoreTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private UserStore userStore = new UserStore(new CrudRepository(sf));

    @Test
    void add2UserAndGet() {
        User userOne = new User("mail1", "phone1");
        userStore.add(userOne);
        User userTwo = new User("mail2", "phone2");
        userStore.add(userTwo);
        assertThat(userOne.getId()).isEqualTo(1);
        assertThat(userTwo.getId()).isEqualTo(2);
        Optional<User> userDbOne = userStore.get(userOne.getEmail(), userOne.getPhone());
        Optional<User> userDbTwo = userStore.get(userTwo.getEmail(), userTwo.getPhone());
        assertThat(userDbOne).isPresent();
        assertThat(userDbTwo).isPresent();
    }
}