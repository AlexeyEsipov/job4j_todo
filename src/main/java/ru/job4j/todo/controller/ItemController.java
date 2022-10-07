package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.CategoryService;
import ru.job4j.todo.service.ItemService;

@ThreadSafe
@Controller
public class ItemController {
    private static final String ALL_TASKS = "Все задания пользователя ";
    private static final String NOT_COMPLETED = "Незавершенные задания ";
    private static final String COMPLETED = "Завершенные задания ";
    private final ItemService itemService;
    private final CategoryService categoryService;

    public ItemController(ItemService service, CategoryService categoryService) {
        this.itemService = service;
        this.categoryService = categoryService;
    }

    @GetMapping("/items")
    public String items(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", itemService.findAll(user));
        model.addAttribute("message", ALL_TASKS);
        return "items";
    }

    @GetMapping("/formAddItem")
    public String formAddItem(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("categories", categoryService.findAll());
        return "addItem";
    }

    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item,
                             @RequestParam(value = "category.id", required = false) List<Integer> categoriesId,
                             HttpSession session) {
        Set<Category> categories = new HashSet<>();
        for (Integer categoryId : categoriesId) {
            categories.add(categoryService.findById(categoryId));
        }
        item.setCategories(categories);
        item.setUser((User) session.getAttribute("user"));
        itemService.add(item);
        return "redirect:/items";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item,
                             @RequestParam(value = "category.id", required = false) List<Integer> categoriesId,
                             HttpSession session) {
        Set<Category> categories = new HashSet<>();
        for (Integer categoryId : categoriesId) {
            categories.add(categoryService.findById(categoryId));
        }
        item.setCategories(categories);
        item.setUser((User) session.getAttribute("user"));
        itemService.update(item.getId(), item);
        return "redirect:/items";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdateItem(Model model, @PathVariable("itemId") int id, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "updateItem";
    }

    @GetMapping("/completed")
    public String completed(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", itemService.completed(user));
        model.addAttribute("message", COMPLETED);
        return "items";
    }

    @GetMapping("/notCompleted")
    public String fresh(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", itemService.notCompleted(user));
        model.addAttribute("message", NOT_COMPLETED);
        return "items";
    }

    @GetMapping("/itemDetails/{itemId}")
    public String itemDetails(Model model, @PathVariable("itemId") int id, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("item", itemService.findById(id));
        return "itemDetails";
    }

    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") int id) {
        itemService.delete(id);
        return "redirect:/items";
    }

    @GetMapping("/completed/{itemId}")
    public String completedId(@PathVariable("itemId") int id) {
        itemService.completedId(id);
        return "redirect:/items";
    }
}