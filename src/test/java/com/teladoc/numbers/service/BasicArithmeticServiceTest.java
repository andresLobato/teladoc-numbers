package com.teladoc.numbers.service;

import com.teladoc.numbers.api.request.BasicArithmeticRequestDTO;
import com.teladoc.numbers.api.response.BasicArithmeticResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class BasicArithmeticServiceTest {


    private BasicArithmeticService basicArithmeticService;

    @Before
    public void setup() {

        this.basicArithmeticService = new BasicArithmeticService();
    }

    @Test
    public void testSumSucceed() {

        BasicArithmeticRequestDTO request1 = createRequest("123456789012345678901", "12345678");
        BasicArithmeticResponseDTO responseDTO1 = basicArithmeticService.sum(request1);
        assertEquals(responseDTO1.getResult(), "123456789012358024579");

        BasicArithmeticRequestDTO request2 = createRequest("123", "11");
        BasicArithmeticResponseDTO responseDTO2 = basicArithmeticService.sum(request2);
        assertEquals(responseDTO2.getResult(), "134");

        BasicArithmeticRequestDTO request3 = createRequest("8078237265590701714569690889594875927043510862118328746000",
                "48499410160993677595112011314721050387183026539910581501532746992747596937148353865126145435");
        BasicArithmeticResponseDTO responseDTO3 = basicArithmeticService.sum(request3);
        assertEquals(responseDTO3.getResult(),
                "48499410160993677595112011314721058465420292130612296071223636587623523980659215983454891435");
    }

    @Test
    public void testInvertedSumSucceed() {

        BasicArithmeticRequestDTO request1 = createRequest("12345678", "123456789012345678901");
        BasicArithmeticResponseDTO responseDTO1 = basicArithmeticService.sum(request1);
        assertEquals(responseDTO1.getResult(), "123456789012358024579");

        BasicArithmeticRequestDTO request2 = createRequest("11", "123");
        BasicArithmeticResponseDTO responseDTO2 = basicArithmeticService.sum(request2);
        assertEquals(responseDTO2.getResult(), "134");

        BasicArithmeticRequestDTO request3 =
                createRequest("48499410160993677595112011314721050387183026539910581501532746992747596937148353865126145435",
                "8078237265590701714569690889594875927043510862118328746000");
        BasicArithmeticResponseDTO responseDTO3 = basicArithmeticService.sum(request3);
        assertEquals(responseDTO3.getResult(),
                "48499410160993677595112011314721058465420292130612296071223636587623523980659215983454891435");
    }

    private BasicArithmeticRequestDTO createRequest(final String number1, final String number2) {
        return BasicArithmeticRequestDTO.builder()
                .number1(number1)
                .number2(number2)
                .build();
    }

}
