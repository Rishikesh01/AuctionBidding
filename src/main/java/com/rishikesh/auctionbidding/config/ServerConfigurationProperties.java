package com.rishikesh.auctionbidding.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.bidder-client")
public class ServerConfigurationProperties {
    private String url;
    private String endpoint;
}
