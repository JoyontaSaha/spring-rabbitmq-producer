package com.joyontasaha.springrabbitmqproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/test")
    public String test() {
        return "test passed.";
    }

    @PostMapping("/publish1")
    public String publish1(@RequestBody CustomMessage message) {
        log.info("message1 {}", message);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY_1, message);
        log.info("publish1 message {}", message);
        return "Message1 Published Successfully.";
    }

    @PostMapping("/publish2")
    public String publish2(@RequestBody CustomMessage message) {
        log.info("message2 {}", message);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY_2, message);
        log.info("publish2 message {}", message);
        return "Message2 Published Successfully.";
    }
}
