package com.ms.user.producers;

// This class responsibility is to publish the messages to the broker.

import com.ms.user.dtos.EmailDTO;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDTO();
        emailDto.setId(userModel.getId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("You are signed up!");
        emailDto.setText("Welcome, " + userModel.getName() + "! We are glad you are with us.");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}