package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.service.TweetService;
import pl.mm.pitupiter.service.UserService;

@Controller
public class MainController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String tweetList(Model model){
        model.addAttribute("allTweets", tweetService.getAllTweets());
        model.addAttribute("newUser", new User());
        return "home";
    }
    @PostMapping("/")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/";
    }


}

