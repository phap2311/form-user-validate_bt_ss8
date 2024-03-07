package com.example.create_form_use_validate.controller;

import com.example.create_form_use_validate.model.User;
import com.example.create_form_use_validate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("")
    public String index(Model model) {
        List<User> userList = iUserService.findAll();
        model.addAttribute("user", userList);
        return "/index";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("user", new User());
        return "/create";
    }

    @PostMapping("/create")
    public String createForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/index";
        } else {
            model.addAttribute("user", user);
            iUserService.save(user);
            return "/result";
        }

    }
}
