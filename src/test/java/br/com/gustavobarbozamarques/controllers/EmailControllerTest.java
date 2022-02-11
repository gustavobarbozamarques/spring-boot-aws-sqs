package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.EmailRequestDTO;
import br.com.gustavobarbozamarques.services.JMSEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JMSEmailService jmsEmailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSendEmailShouldReturnSuccess() throws Exception {
        var emailRequest = EmailRequestDTO
                .builder()
                .to("Recipient")
                .subject("Subject")
                .body("Body")
                .build();

        this.mockMvc
                .perform(
                        post("/email/send")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(emailRequest))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testSendEmailShouldReturnBadRequestWhenFieldsMissing() throws Exception {
        var emailRequest = EmailRequestDTO
                .builder()
                .build();

        this.mockMvc
                .perform(
                        post("/email/send")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(emailRequest))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
