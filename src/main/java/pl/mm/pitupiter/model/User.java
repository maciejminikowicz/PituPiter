package pl.mm.pitupiter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserAccountType userAccountType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Tweet> tweetList;

    @OneToMany
    private List<Comment> commentList;
}
