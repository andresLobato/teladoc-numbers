package com.teladoc.numbers.api.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
public class BasicArithmeticRequestDTO implements Serializable {

    @NotBlank(message = "The number to add cannot be empty")
    @Pattern(regexp = "^[0-9]+$", message = "The number to add can only contain digits")
    private String number1;

    @NotBlank(message = "The number to add cannot be empty")
    @Pattern(regexp = "^[0-9]+$", message = "The number to add can only contain digits")
    private String number2;

}
