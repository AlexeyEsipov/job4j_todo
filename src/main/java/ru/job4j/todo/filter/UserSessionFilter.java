package ru.job4j.todo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.job4j.todo.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
public class UserSessionFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(UserSessionFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUserName("Гость");
        }
        req.setAttribute("user", user);
        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            LOG.error("Exception in UserSessionFilter#doFilter user != null", e);
        }
    }
}
