package br.com.gustavobarbozamarques.services;

import br.com.gustavobarbozamarques.dto.EmailRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JMSEmailService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${sqs.queue}")
    private String queue;

    public void sendMessage(EmailRequestDTO emailRequestDTO){
        jmsTemplate.convertAndSend(queue, emailRequestDTO);
    }

    @JmsListener(destination = "${sqs.queue}")
    public void receiveMessage(EmailRequestDTO emailRequestDTO){
        log.info("Received: {}", emailRequestDTO);
    }

}