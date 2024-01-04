package com.ll.medium.domain.user.service;

import com.ll.medium.domain.user.dto.AddUserRequest;
import com.ll.medium.domain.user.entity.User;
import com.ll.medium.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Add User")
    public void AddUserTest() {
        AddUserRequest addUserRequest = AddUserRequest.builder()
                .email("email1")
                .password("password1")
                .build();

        Long userId = userService.save(addUserRequest);

        Optional<User> savedUser = userRepository.findById(userId);
        assertThat(savedUser).isPresent();
        assertThat(savedUser.get().getEmail()).isEqualTo(addUserRequest.getEmail());
    }

}
