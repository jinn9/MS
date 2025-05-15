package com.github.jinn9.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;

    private String email;

    private AddressDto address;
}
