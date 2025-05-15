package com.github.jinn9.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    @NotEmpty(message = "city cannot be empty")
    private String city;

    @NotEmpty(message = "province cannot be empty")
    private String province;
}
