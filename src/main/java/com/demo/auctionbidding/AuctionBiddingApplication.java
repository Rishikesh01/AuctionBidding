package com.demo.auctionbidding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@EnableAsync
@SpringBootApplication
public class AuctionBiddingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionBiddingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder)
    {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(200)).setReadTimeout(Duration.ofMillis(200)).build();
    }

}
