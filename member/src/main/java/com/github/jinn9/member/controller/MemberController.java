package com.github.jinn9.member.controller;

import com.github.jinn9.member.dto.ResponseDto;
import com.github.jinn9.member.dto.SignupDto;
import com.github.jinn9.member.entity.Member;
import com.github.jinn9.member.exception.MemberExistsException;
import com.github.jinn9.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/api/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody @Validated SignupDto signupDto) {
        Optional<Member> memberOptional = memberRepository.findByEmail(signupDto.getEmail());
        if (memberOptional.isPresent()) {
            throw new MemberExistsException("A user with " + signupDto.getEmail() + " already exists.");
        }

        Member member = new Member(signupDto.getEmail(), signupDto.getPassword());
        memberRepository.save(member);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Member created successfully."));
    }
}
