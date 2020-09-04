package app.user.service;

import app.domain.user.domain.User;
import app.domain.user.dto.UserDto;
import app.domain.user.exception.UserAlreadyExistedException;
import app.domain.user.exception.UserNotFoundException;
import app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long saveUser(UserDto.RegistUserInfo dto){
        User user = dto.toEntity();
        validateDuplicateUser(user);
        return userRepository.save(user).getId();
    }

    private void validateDuplicateUser(User user){
        List<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(!findUsers.isEmpty()){
            throw new UserAlreadyExistedException("이미 존재하는 회원입니다.");
        }
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public UserDto.MyInfo findUser(Long userId){
        return userRepository
                .findById(userId)
                .map(user -> modelMapper.map(user, UserDto.MyInfo.class))
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void updateUser(Long userId, UserDto.UpdateUserInfo dto){
        User savedUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        savedUser.updateTo(dto.getUsername(), dto.getPassword());
    }

    @Transactional
    public void deleteUser(Long userId){
        User savedUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(savedUser);
    }
}
