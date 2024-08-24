package com.example.demo.repositroy;

import com.example.demo.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    List<Member> findByNick(String nick);
}
