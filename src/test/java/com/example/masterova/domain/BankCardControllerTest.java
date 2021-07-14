package com.example.masterova.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// @RunWith(SpringRunner.class)
@WebMvcTest(BankCardController.class)
@ExtendWith(SpringExtension.class)



class BankCardControllerTest {

    @Autowired MockMvc mvc;
    @MockBean BankCardController bankCardController;

    @Test
    void getCards() throws Exception {
        /*RequestBuilder request = MockMvcRequestBuilders.get();
        MvcResult result = mvc.perform(request).andReturn();
        assertNotNull(result.getResponse().getContentAsString());*/

        mvc.perform(getCards("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(result)))
                .andExpect(StatusAssertions.isOk());
    }


    @Test
    void getCard() {

    }
}

/*BankCardController bankCardController = new BankCardController();
        List<BankCard> response = bankCardController.getCards();
        assertNotNull(response);*/