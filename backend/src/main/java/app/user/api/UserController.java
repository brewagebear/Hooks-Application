package app.user.api;

import app.domain.user.domain.User;
import app.domain.user.dto.UserDto;
import app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity getUsersV1(){
        List<User> findUsers = userService.findUsers();
        List<UserDto.MyInfo> collect = findUsers.stream()
                .map(user -> UserDto.MyInfo.builder().username(user.getUserName()).email(user.getEmail()).build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UserDto.GetUserAllInfo<>(collect.size(), collect));
    }

    @GetMapping("{id}")
    public ResponseEntity getUserV1(@PathVariable("id") Long userId){
        UserDto.MyInfo userInfo = userService.findUser(userId);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping
    public ResponseEntity saveUserV1(@RequestBody UserDto.RegistUserInfo request){
        Long savedUserId = userService.saveUser(request);
        return ResponseEntity.created(URI.create("/api/v1/users/" + savedUserId)).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateUserV1(
            @PathVariable("id") Long userId,
            @RequestBody UserDto.UpdateUserInfo request){

        userService.updateUser(userId, request);
        UserDto.MyInfo userInfo = userService.findUser(userId);
        return ResponseEntity.ok(userInfo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUserV1(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

