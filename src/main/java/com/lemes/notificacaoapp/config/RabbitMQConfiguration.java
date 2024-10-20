package com.lemes.notificacaoapp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Jackson2JsonMessageConverter Jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
