package app.user.api;

import app.domain.user.domain.*;
import app.domain.user.dto.UserDto;
import app.util.AcceptanceTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserAcceptanceTest {

    @Autowired
    private WebTestClient webTestClient;

    private String userId;

    @BeforeEach
    @DisplayName("사용자 저장 API 테스트")
    void createUser(){

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(GenreEnum.HIPHOP.toString()));
        genres.add(new Genre(GenreEnum.KPOP.toString()));

        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(JobEnum.DJBEATEMAKER.toString()));
        jobs.add(new Job(JobEnum.RAPPER.toString()));

        UserDto.RegistUserInfo registUserInfo = new UserDto.RegistUserInfo(
                "seansin@cbnu.ac.kr",
                "seansin",
                "zxcv123",
                jobs,
                genres);


        WebTestClient.ResponseSpec responseSpec = webTestClient.post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(registUserInfo), UserDto.RegistUserInfo.class)
                    .exchange()
                    .expectStatus()
                    .isCreated()
                .expectHeader().valueMatches("Location", "/api/v1/users/[1-9]+[0-9]*");

        userId = AcceptanceTestUtils.extractDomainIdFromCreatedResourceAddress(responseSpec);
    }

    @Test
    @DisplayName("전체 유저 정보 조회 테스트")
    void showAllUsers(){
        webTestClient.get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.length", greaterThan(1));
    }

    @Test
    @DisplayName("단일 유저 정보 조회 테스트")
    void showUser(){
        webTestClient.get()
                .uri("/api/v1/users/" + userId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.username", "seansin");
    }

    @Test
    @DisplayName("유저 정보 업데이트 테스트")
    void updateUser(){

        UserDto.UpdateUserInfo updateUserInfo = new UserDto.UpdateUserInfo("ddddd", "12345678");

        webTestClient.put()
                .uri("/api/v1/users/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateUserInfo), UserDto.UpdateUserInfo.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.username", "ddddd");
    }

    @Test
    @DisplayName("유저 삭제 테스트")
    void deleteUser(){
        webTestClient.delete()
                .uri("/api/v1/users/" + userId)
                .exchange()
                .expectStatus()
                .isNoContent();
    }
}