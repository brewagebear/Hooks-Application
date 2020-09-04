package app.domain.user.domain;

import app.util.Enum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Genre {

    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enum(enumClass = GenreEnum.class, ignoreCase = true)
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }
}
