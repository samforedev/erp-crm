package com.sam.insuranceservice.infraestructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${rabbitmq.user.exchange}")
    private String userExchange;
    @Value("${rabbitmq.user.queue}")
    private String userQueue;
    @Value("${rabbitmq.user.routing-key}")
    private String userRoutingKey;

    @Bean
    public Exchange userEventExchange() {
        return ExchangeBuilder.topicExchange(userExchange).durable(true).build();
    }

    @Bean
    public Queue userCreationQueue() {
        return new Queue(userQueue, true);
    }

    @Bean
    public Binding bindingUserCreation() {
        return BindingBuilder
                .bind(userCreationQueue())
                .to(userEventExchange())
                .with(userRoutingKey)
                .noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
