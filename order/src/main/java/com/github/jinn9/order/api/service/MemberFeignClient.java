package com.github.jinn9.order.api.service;

import com.github.jinn9.order.api.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("member")
public interface MemberFeignClient {

    @GetMapping("/api/members/{memberId}")
    ResponseEntity<Member> findMember(@PathVariable("memberId") Long memberId);
}