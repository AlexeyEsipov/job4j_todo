package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ThreadSafe
@Repository
public class CategoryStore {
    private final CrudRepository crudRepository;

    public Category add(Category category) {
        crudRepository.run(session -> session.save(category));
        return category;
    }

    public List<Category> findAll() {
        return crudRepository.query("from Category", Category.class);
    }

    public Category findById(int id) {
        return crudRepository.optional(
                "from Category where id = :fId", Category.class,
                Map.of("fId", id)).orElse(new Category("no such category"));
    }
}