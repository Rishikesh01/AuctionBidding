package com.client.biddingclient.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Data
@Configuration
@ConfigurationProperties("application.server")
public class BidderClientConfiguration {
    private String url;
    private String bidderURL;
    private String endpoint;
}
