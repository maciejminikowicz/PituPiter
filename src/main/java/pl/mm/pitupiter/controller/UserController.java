package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.service.TweetService;
import pl.mm.pitupiter.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

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

    @GetMapping()
    public String getUserDetails(Model model) {
        getLoggedInUser(model);
        return "user-details";
    }

    @GetMapping("/details")
    public String getPublicUserDetails(Model model, @RequestParam String username){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userService.findUserByUsername(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            model.addAttribute("userDetails", user);
            model.addAttribute("id", user.getId());
            model.addAttribute("loggedInUserId", loggedInUser.getId());
            model.addAttribute("userTweets", tweetService.getUserTweets(user));
        }
        return "public-user";
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
        Optional<User> optionalUser = userService.findUserById(user.getId());
        if (optionalUser.isPresent()){
            User user2 = optionalUser.get();
            model.addAttribute("userTweets", tweetService.getUserTweets(user2));
        }
        return user;
    }


    @GetMapping("/home")
    public String hello(Principal principal, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("name", principal.getName());
        model.addAttribute("id", user.getId());
        model.addAttribute("allTweets", tweetService.getAllTweets());
        return "home-user";
    }

    @GetMapping("/users")
    public String getUsers(Model model, @RequestParam String username){
        model.addAttribute("users", userService.findByName(username));
        return "search-results";
    }



}
