package com.example.demo.services;

import com.example.demo.models.dtos.LoginUserDto;
import com.example.demo.models.entities.User;
import com.example.demo.models.dtos.UserRegistrationDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final LoggedUser userSession;

    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(UserRegistrationDto userRegistrationDto) {
        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return false;
        }

        Optional<User> optUserEmail = this.userRepository.findByEmail(userRegistrationDto.getEmail());
        if (optUserEmail.isPresent()) {
            return false;
        }

        Optional<User> optUserByUsername = this.userRepository.findByUsername(userRegistrationDto.getUsername());
        if (optUserByUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setFullName(userRegistrationDto.getFullName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        userRepository.save(user);

        return true;
    }

    public boolean login(LoginUserDto loginUserDto) {
        Optional<User> optUser = this.userRepository.
                findByUsernameAndPassword(loginUserDto.getUsername(), loginUserDto.getPassword());
        if (optUser.isEmpty()) {
            return false;
        }

        this.userSession.login(optUser.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() > 0;
    }
}
