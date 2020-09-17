package pl.mm.pitupiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.pitupiter.model.Comment;
import pl.mm.pitupiter.model.Tweet;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.repository.CommentRepository;
import pl.mm.pitupiter.repository.TweetRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;


    public void saveComment(Comment comment, Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);
        if (optionalTweet.isPresent()) {
            Tweet tweet = optionalTweet.get();
            User user = tweet.getUser();
            comment.setTweet(tweet);
            comment.setCommentUser(user);
            commentRepository.save(comment);
        }
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }


    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }


}
