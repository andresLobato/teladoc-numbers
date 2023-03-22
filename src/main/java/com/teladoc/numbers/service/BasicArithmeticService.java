package com.teladoc.numbers.service;

import com.teladoc.numbers.api.request.BasicArithmeticRequestDTO;
import com.teladoc.numbers.api.response.BasicArithmeticResponseDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <h2>BasicArithmeticService</h2>
 * <p>
 * Service for Basic Arithmetic Operations
 */
@Service
@NoArgsConstructor
@Slf4j
public class BasicArithmeticService {

    /**
     * The sum function takes two string arguments number1 and number2 and returns their sum as a string.
     * The function works by iterating over the digits of both strings from right to left,
     * adding them along with any carry from the previous addition, and building the result string one digit at a time.
     * The StringBuilder class is used to efficiently build the result string by appending each digit
     * to the end of the string.
     * @param request a {@link BasicArithmeticRequestDTO}
     * @return a {@link BasicArithmeticResponseDTO} with original numbers and the result.
     */
    public BasicArithmeticResponseDTO sum(final BasicArithmeticRequestDTO request) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = request.getNumber1().length() - 1;
        int j = request.getNumber2().length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = i >= 0 ? request.getNumber1().charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? request.getNumber2().charAt(j) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            result.append(digit);
            i--;
            j--;
        }
        return BasicArithmeticResponseDTO.builder()
                .number1(request.getNumber1())
                .number2(request.getNumber2())
                .result(result.reverse().toString())
                .build();
    }

}
