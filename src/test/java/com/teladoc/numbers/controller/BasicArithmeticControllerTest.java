package com.teladoc.numbers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teladoc.numbers.api.request.BasicArithmeticRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class BasicArithmeticControllerTest {

    private static final String BASIC_ARITHMETIC_SUM_URL = "/basic/arithmetic/sum";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .build();
    }

    @Test
    public void testSuccessfulSum() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.post(BASIC_ARITHMETIC_SUM_URL)
                    .content(this.serialize(createRequest("12345678", "123456789012345678901")))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
                .andExpect(content().string(containsString("123456789012358024579")));
    }

    @Test
    public void testEmptyNumber() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASIC_ARITHMETIC_SUM_URL)
                .content(this.serialize(createRequest("", "123456789012345678901")))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testNotNumber() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASIC_ARITHMETIC_SUM_URL)
                .content(this.serialize(createRequest("-172635474", "123456789012345678901")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private <T> String serialize(final T source) throws JsonProcessingException {

        if (source == null) {
            return null;
        }
        return this.objectMapper.writeValueAsString(source);
    }

    private <T> T deserialize(final String jsonString, final TypeReference<T> type) throws JsonProcessingException {

        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return this.objectMapper.readValue(jsonString, type);
    }

    private BasicArithmeticRequestDTO createRequest(final String number1, final String number2) {
        return BasicArithmeticRequestDTO.builder()
                .number1(number1)
                .number2(number2)
                .build();
    }

}
