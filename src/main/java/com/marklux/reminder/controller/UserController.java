package com.marklux.reminder.controller;

import com.marklux.reminder.domain.User;
import com.marklux.reminder.service.UserService;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lumin on 18/6/21.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "app/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute User user, Model model, HttpServletRequest request) {
        User checkedUser = null;
        try {
            checkedUser = this.userService.login(user);
        }catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "app/login";
        }
        if (checkedUser != null) {
            request.getSession().setAttribute("user", checkedUser);
            return "redirect:home";
        }else {
            model.addAttribute("errorMessage", "未知错误，登录失败！");
            return "app/login";
        }
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "app/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute User user, Model model, HttpServletRequest request) {
        try {
            userService.createUser(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "app/register";
        }
        return "redirect:index";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().removeAttribute("user");
            return "redirect:index";
        }else {
            return "redirect:login";
        }
    }
}
