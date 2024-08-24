package com.example.demo.repositroy;

import com.example.demo.domain.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findTopByOrderByIdDesc();
}
