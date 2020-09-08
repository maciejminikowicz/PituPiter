package pl.mm.pitupiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mm.pitupiter.model.Tweet;
import pl.mm.pitupiter.model.User;
import pl.mm.pitupiter.repository.TweetRepository;
import pl.mm.pitupiter.repository.UserRepository;

import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;


    public void createTweet(Tweet tweet, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            tweet.setUser(user);
            tweetRepository.save(tweet);
        }
    }

    public Optional<Tweet> findById(Long tweetId) {
        return tweetRepository.findById(tweetId);
    }

    public void deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }


}
