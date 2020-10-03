package pl.mm.pitupiter.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mm.pitupiter.model.Tweet;
import pl.mm.pitupiter.service.TweetService;

import java.util.Optional;

@Controller
@RequestMapping("/tweet")
@AllArgsConstructor
@NoArgsConstructor
public class TweetController {

    @Autowired
    private TweetService tweetService;


    @GetMapping("/add")
    public String tweetAddForm(Model model, @RequestParam("user_Id") Long userId) {
        model.addAttribute("newTweet", new Tweet());
        model.addAttribute("userId", userId);
        return "tweet-form";
    }

    @PostMapping("/add")
    public String addTweet(Tweet tweet, Long tweetUserId) {
        tweetService.createTweet(tweet, tweetUserId);
        return "redirect:/user/home";
    }
// ZAKOMENTOWALEM BO MODEL.ADDATTRIBUTE NEW TWEET BYL POWIAZYWANY ZAMIAST TEGO Z /ADD
    @GetMapping("/edit/{tweetId}")
    public String editTweet(Model model, @PathVariable Long tweetId) {
        Optional<Tweet> optionalTweet = tweetService.findById(tweetId);
        if (optionalTweet.isPresent()) {
            Tweet tweet = optionalTweet.get();
            model.addAttribute("newTweet", tweet);
            model.addAttribute("userId", tweet.getUser().getId());
            return "tweet-form";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String deleteTweet(@RequestParam Long tweetId, Long userId) {
        Optional<Tweet> optionalTweet = tweetService.findById(tweetId);
        if (optionalTweet.isPresent()) {
            Tweet tweet = optionalTweet.get();
            userId = tweet.getUser().getId();
        }
        tweetService.deleteTweet(tweetId);
        return "redirect:/user/details?userId=" + userId;
    }

    @GetMapping("/details")
    public String getTweetDetails(Model model, @RequestParam Long tweetId) {
        Optional<Tweet> optionalTweet = tweetService.findById(tweetId);
        if (optionalTweet.isPresent()) {
            Tweet tweet = optionalTweet.get();
            model.addAttribute("tweetDetails", tweet);
            model.addAttribute("userId", tweet.getUser().getId());
            return "tweet-details";
        } else {
            return "redirect:/";
        }
    }

//    @GetMapping("/list")
//    public String tweetList(Model model){
//        model.addAttribute("allTweets", tweetService.getAllTweets());
//        return "home";
//    }



}
