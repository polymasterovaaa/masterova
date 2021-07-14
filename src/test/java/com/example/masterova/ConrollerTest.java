package com.example.masterova;

import com.example.masterova.domain.BankCardController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(BankCardController.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankCardRepo bankCardRepo;

    private final BankCard firstCard = new BankCard(1L, "firstNumber", "firstAccNumber", LocalDate.now(), "firstCvv", "firstOwner");
    private final BankCard secondCard = new BankCard(2L, "secondNumber", "secondAccNumber", LocalDate.now(), "secondCvv", "secondOwner");

    @Test
    public void getCardsTest() throws Exception {
        List<BankCard> localList = new ArrayList<>(Arrays.asList(/*firstCard, secondCard*/));
        Mockito.when(bankCardRepo.findAll()).thenReturn(localList);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cards").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().equals("[]"));
    }

    @Test
    public void getCardByIdTest() throws Exception {
        Mockito.when(bankCardRepo.getById(firstCard.getId())).thenReturn(firstCard);
        Mockito.when(bankCardRepo.getById(3L)).thenReturn(null);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/cards/1").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isBlank());
        MvcResult secondResult = mockMvc.perform(MockMvcRequestBuilders.get("/cards/3").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertTrue(secondResult.getResponse().getContentAsString().isBlank());
    }
}
