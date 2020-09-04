package app.domain.user.dto;


import app.domain.user.domain.Genre;
import app.domain.user.domain.Job;
import app.domain.user.domain.User;
import lombok.*;

import java.util.List;

public class UserDto {

    @Data
    @Getter
    public static class RegistUserInfo {

        private String email;
        private String username;
        private String password;
        private List<Job> jobs;
        private List<Genre> genres;

        public User toEntity(){
            return User.builder()
                    .email(email)
                    .userName(username)
                    .password(password)
                    .likeJobs(jobs)
                    .likeGenres(genres)
                    .build();
        }

        @Builder
        public RegistUserInfo(String email,
                              String username,
                              String password,
                              List<Job> jobs,
                              List<Genre> genres){
            this.email = email;
            this.username = username;
            this.password = password;
            this.jobs = jobs;
            this.genres = genres;
        }

        @Override
        public String toString() {
            return "RegistUserInfo{" +
                    "email='" + email + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", jobs=" + jobs +
                    ", genres=" + genres +
                    '}';
        }
    }

    @Data
    @Getter
    @NoArgsConstructor
    public static class UpdateUserInfo {
        private String username;
        private String password;

        public UpdateUserInfo(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @Data
    @Getter
    @NoArgsConstructor
    public static class MyInfo {
        private Long userId;
        private String email;
        private String username;

        @Builder
        public MyInfo(Long userId, String email, String username) {
            this.userId = userId;
            this.email = email;
            this.username = username;
        }
    }

    @Data
    @AllArgsConstructor
    public static class GetUserAllInfo<T> {
        private int count;
        private T data;
    }
}
