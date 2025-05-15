package com.github.jinn9.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequestDto {
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @NotNull(message = "address cannot be null")
    @Valid
    private AddressDto address;
}
