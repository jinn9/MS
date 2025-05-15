package com.github.jinn9.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "price cannot be null")
    @Min(value = 0, message = "price must be greater than or equal to 0")
    private Integer price;

    @NotNull(message = "stockQuantity cannot be null")
    @Min(value = 0, message = "stockQuantity must be greater than or equal to 0")
    private Integer stockQuantity;
}
