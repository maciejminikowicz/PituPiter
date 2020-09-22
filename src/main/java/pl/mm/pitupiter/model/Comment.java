package pl.mm.pitupiter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.DependsOn;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    

    @CreationTimestamp
    private LocalDateTime dateCommentAdded;

    @ManyToOne
    private Tweet tweet;

    @ManyToOne
    private User commentUser;

}
