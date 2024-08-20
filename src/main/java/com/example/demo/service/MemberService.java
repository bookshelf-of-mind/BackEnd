package com.example.demo.service;

import com.example.demo.domain.member.Member;
import com.example.demo.repositroy.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAllUsers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member saveNickname(Member nick) {
        return memberRepository.save(nick);
    }
}
