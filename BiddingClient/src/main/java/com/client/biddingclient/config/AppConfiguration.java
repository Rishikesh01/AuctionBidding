package com.client.biddingclient.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Configuration
@AllArgsConstructor
public class AppConfiguration {
    private final BidderClientConfiguration clientConfiguration;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(clientConfiguration.getUrl()).build();
    }
}
