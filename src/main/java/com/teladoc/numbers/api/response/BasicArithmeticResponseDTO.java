package com.teladoc.numbers.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class BasicArithmeticResponseDTO implements Serializable {

    private String number1;

    private String number2;

    private String result;

}
