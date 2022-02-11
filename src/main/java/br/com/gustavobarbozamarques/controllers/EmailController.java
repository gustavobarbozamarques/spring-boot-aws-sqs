package br.com.gustavobarbozamarques.controllers;

import br.com.gustavobarbozamarques.dto.EmailRequestDTO;
import br.com.gustavobarbozamarques.services.JMSEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "AWS SQS")
@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    private JMSEmailService jmsEmailService;

    @PostMapping("/send")
    @ApiOperation("Send email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success")
    })
    public void sendEmail(@RequestBody @Valid EmailRequestDTO emailRequestDTO) {
        jmsEmailService.sendMessage(emailRequestDTO);
    }
}
