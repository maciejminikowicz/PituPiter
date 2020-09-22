package pl.mm.pitupiter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mm.pitupiter.model.Comment;
import pl.mm.pitupiter.model.Tweet;
import pl.mm.pitupiter.service.CommentService;
import pl.mm.pitupiter.service.TweetService;

import java.util.Optional;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private TweetService tweetService;

    @GetMapping("/add")
    public String getCommentForm(Model model, @RequestParam("tweet_id") Long tweetId) {
        Optional<Tweet> optionalTweet = tweetService.findById(tweetId);
        if (optionalTweet.isPresent()) {
            Tweet tweet = optionalTweet.get();
            String content = tweet.getContent();
            model.addAttribute("newComment", new Comment());
            model.addAttribute("tweetId", tweetId);
            model.addAttribute("tweetContent", content);
            return "comment-form";
        } else return "redirect:/";
    }

    @PostMapping("/add")
    public String addComment(Comment comment, Long tweetId) {
        commentService.saveComment(comment, tweetId);
        return "redirect:/tweet/details?tweetId=" + tweetId;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam Long commentId, Long tweetId) {
        Optional<Comment> optionalComment = commentService.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            tweetId = comment.getTweet().getId();
        }
        commentService.deleteComment(commentId);
        return "redirect:/tweet/details?tweetId=" + tweetId;
    }


}
