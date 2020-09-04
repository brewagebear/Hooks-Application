package app.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Job> likeJobs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Genre> likeGenres = new ArrayList<>();

    public void addJobs(List<Job> jobs){
        likeJobs.addAll(jobs);
    }

    public void addJobs(Job job){
        likeJobs.add(job);
    }

    public void addGenres(List<Genre> genres){
        likeGenres.addAll(genres);
    }

    public void addGenres(Genre genre){
        likeGenres.add(genre);
    }

    @Builder
    public User(String userName,
                String email,
                String password,
                List<Job> likeJobs,
                List<Genre> likeGenres) {

        this.userName = userName;
        this.email = email;
        this.password = password;
        addGenres(likeGenres);
        addJobs(likeJobs);
    }

    public void updateTo(String username,
                         String password,
                         Job likeJobs,
                         Genre likeGenres){
        this.userName = username;
        this.password = password;
        addJobs(likeJobs);
        addGenres(likeGenres);
    }

    public void updateTo(String username, String password) {
        this.userName = username;
        this.password = password;
    }
}


