package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.service.UserService;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegisterUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "user-form";
    }

    @PostMapping
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}
