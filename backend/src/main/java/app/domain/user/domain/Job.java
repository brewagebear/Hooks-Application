package app.domain.user.domain;

import app.util.Enum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Job {

    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enum(enumClass = JobEnum.class, ignoreCase = true)
    private String job;

    public Job(String job) {
        this.job = job;
    }
}
