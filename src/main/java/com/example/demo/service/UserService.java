package com.example.demo.service;


import com.example.demo.repositroy.UserRepository;
import jakarta.transaction.Transactional;
import com.example.demo.domain.member.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findLastUser() {
        return userRepository.findTopByOrderByIdDesc(); // id를 기준으로 마지막 유저를 가져오는 메서드
    }

    public String findLastNick() {
        User lastUser = userRepository.findTopByOrderByIdDesc();
        return lastUser != null ? lastUser.getNick() : null;
    }
}
