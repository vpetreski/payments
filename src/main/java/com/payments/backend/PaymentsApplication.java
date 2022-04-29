package com.payments.backend;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class PaymentsApplication {

    @Bean
    public RedisTemplate<String, Payment> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Payment> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info().title("Payments Application API").description("Payments Backend with Spring Boot"));
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

}
