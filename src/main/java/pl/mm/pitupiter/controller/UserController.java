package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterUserForm(Model model) {
        model.addAttribute("newUser", new User());
        return "user-form";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerUser(user);
        Long id = user.getId();
        return "redirect:/user/details?userId=" + id;
    }

    @GetMapping("/edit/{userId}")
    public String editUser(Model model, @PathVariable Long userId) {
        return getUserToEdit(model, userId);
    }

    //TODO: GOTOWE
    @GetMapping("/edit")
    public String editUserParam(Model model, @RequestParam(name = "userId") Long userId) {
        return getUserToEdit(model, userId);
    }

    //TODO: GOTOWE
    private String getUserToEdit(Model model, @RequestParam(name = "userId") Long userId) {
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("newUser", user);
            return "user-form";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/account_deleted";
    }


    @GetMapping("/details")
    public String getUserDetails(Model model, @RequestParam Long userId) {
        Optional<User> optionalUser = userService.findUserById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("userDetails", user);
            return "user-details";
        } else {
            return "redirect:/";
        }
    }


}
