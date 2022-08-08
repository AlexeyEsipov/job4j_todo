package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/allCategory")
    public String allCategory(Model model, HttpSession session) {
        setUser(model, session);
        model.addAttribute("categories", service.findAll());
        return "addCategory";
    }

    @PostMapping("/createCategory")
    public String createCategory(@ModelAttribute Category category) {
        service.add(category);
        return "redirect:/items";
    }

    private void setUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUserName("Гость");
        }
        model.addAttribute("user", user);
    }
}
