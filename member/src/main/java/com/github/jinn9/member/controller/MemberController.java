package com.github.jinn9.member.controller;

import com.github.jinn9.member.dto.AddressDto;
import com.github.jinn9.member.dto.MemberRequestDto;
import com.github.jinn9.member.dto.MemberResponseDto;
import com.github.jinn9.member.dto.ResponseDto;
import com.github.jinn9.member.entity.Address;
import com.github.jinn9.member.entity.Member;
import com.github.jinn9.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<ResponseDto> createMember(@RequestBody @Validated MemberRequestDto memberRequestDto) {
        Member member = new Member(
                memberRequestDto.getEmail(),
                new Address(memberRequestDto.getAddress().getCity(),
                        memberRequestDto.getAddress().getProvince())
        );

        memberService.createMember(member);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Member created"));
    }

    @GetMapping("/api/members/{memberId}")
    public ResponseEntity<MemberResponseDto> findMember(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findMember(memberId);
        MemberResponseDto memberResponseDto = new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                new AddressDto(
                        member.getAddress() != null ? member.getAddress().getCity() : null,
                        member.getAddress() != null ? member.getAddress().getProvince() : null
                ));
        return ResponseEntity.ok(memberResponseDto);
    }
}
