package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //OLD
//    @GetMapping("/edit/{userId}")
//    public String editUser(Model model, @PathVariable Long userId) {
//        return getUserToEdit(model, userId);
//    }

//    @GetMapping("/edit")
//    public String editUserParam(Model model, @RequestParam(name = "userId") Long userId) {
//        return getUserToEdit(model, userId);
//    }

    @GetMapping("/edit")
    public String editUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("editedUser", user);
        return "user-edit-form";
    }

    @PostMapping("/edit")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/account_edited";
    }


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

    @GetMapping("/delete")
    public String deleteUser(Model model) {
        User loggedInUser = getLoggedInUser(model);
        userService.deleteUserById(loggedInUser.getId());
        return "redirect:/account_deleted";
    }

    @GetMapping("/details")
    public String getUserDetails(Model model) {
        getLoggedInUser(model);
        return "user-details";
    }

    private User getLoggedInUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", user.getId());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("accountType", user.getUserAccountType());
        model.addAttribute("role", user.getRole());
        model.addAttribute("authorities", user.getAuthorities());
        model.addAttribute("userDetails", user);
        return user;
    }


    @GetMapping("/home")
    public String hello(Principal principal, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", principal.getName());
        model.addAttribute("id", user.getId());
        return "home-user";
    }


}
