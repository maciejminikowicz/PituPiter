package pl.mm.pitupiter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mm.pitupiter.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


}
