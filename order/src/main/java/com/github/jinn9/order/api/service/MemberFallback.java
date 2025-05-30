package com.github.jinn9.order.api.service;

import com.github.jinn9.order.api.domain.Member;
import com.github.jinn9.order.api.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberFallback implements MemberFeignClient {

    @Override
    public ResponseEntity<Member> findMember(Long memberId) {
        throw new ApiException("Failed to call Member.findMember");
    }
}
