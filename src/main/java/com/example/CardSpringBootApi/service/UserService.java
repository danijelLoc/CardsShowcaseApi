package com.example.CardSpringBootApi.service;

import com.example.CardSpringBootApi.exceptions.IllegalActivationStatusException;
import com.example.CardSpringBootApi.exceptions.UserAlreadyExistException;
import com.example.CardSpringBootApi.exceptions.UserNotFoundException;
import com.example.CardSpringBootApi.model.User;
import com.example.CardSpringBootApi.model.UserStatus;
import com.example.CardSpringBootApi.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByOib(long oib) {
        return userRepository.findById(oib).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User saveUser(User user) {
        userRepository.findById(user.getOib()).ifPresent(existingUser -> { throw new UserAlreadyExistException(); });
        if (user.getStatus() == UserStatus.ACTIVE_CARD_REQUEST) {
            throw new IllegalActivationStatusException();
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long oib) {
        User user = getUserByOib(oib);
        deleteUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteById(user.getOib());
    }

    @Override
    public void updateUser(long oib, UserStatus newStatus, String newCardRequestPath) {
        userRepository.setStatusAndCardRequestPathForUser(newStatus, newCardRequestPath, oib);
    }
}
