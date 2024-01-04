package com.ll.medium.domain.user.repository;

import com.ll.medium.domain.user.entity.User;
import com.ll.medium.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void AddUser() {
        final User user = User.builder()
                .email("email1")
                .password("password1")
                .build();

        User savedUser = (User) userRepository.save(user);

        User retrievedUser = (User) userRepository.findById(savedUser.getId()).orElse(null);

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(retrievedUser.getPassword()).isEqualTo(user.getPassword());
    }
}

