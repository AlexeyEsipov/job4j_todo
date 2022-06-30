package ru.job4j.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.ItemService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/items")
    public String items(Model model, HttpSession session) {
        setUser(model, session);
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", service.findAll(user));
        return "items";
    }

    @GetMapping("/formAddItem")
    public String formAddItem(Model model, HttpSession session) {
        setUser(model, session);
        return "addItem";
    }


    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Item item, HttpSession session) {
        item.setUser((User) session.getAttribute("user"));
        service.add(item);
        return "redirect:/items";
    }

    @GetMapping("/formUpdateItem/{itemId}")
    public String formUpdateItem(Model model, @PathVariable("itemId") int id, HttpSession session) {
        setUser(model, session);
        model.addAttribute("item", service.findById(id));
        return "updateItem";
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item, HttpSession session) {
        item.setUser((User) session.getAttribute("user"));
        service.update(item.getId(), item);
        return "redirect:/items";
    }

    @GetMapping("/completed")
    public String completed(Model model, HttpSession session) {
        setUser(model, session);
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", service.completed(user));
        return "completed";
    }

    @GetMapping("/notCompleted")
    public String fresh(Model model, HttpSession session) {
        setUser(model, session);
        User user = (User) model.getAttribute("user");
        model.addAttribute("items", service.notCompleted(user));
        return "notCompleted";
    }

    @GetMapping("/itemDetails/{itemId}")
    public String itemDetails(Model model, @PathVariable("itemId") int id, HttpSession session) {
        setUser(model, session);
        model.addAttribute("item", service.findById(id));
        return "itemDetails";
    }

    @GetMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") int id) {
        service.delete(id);
        return "redirect:/items";
    }

    @GetMapping("/completed/{itemId}")
    public String completedId(@PathVariable("itemId") int id) {
        service.completedId(id);
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