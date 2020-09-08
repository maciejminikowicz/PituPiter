package pl.mm.pitupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mm.pitupiter.model.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
