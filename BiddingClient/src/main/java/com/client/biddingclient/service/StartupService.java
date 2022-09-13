package com.client.biddingclient.service;

import com.client.biddingclient.dto.BidderRegisterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Component
public class StartupService {
    private final WebClient webClient;
    @Value("${name}")
    private String bidderID;

   private static final String URI = "http://localhost:8080";

    public StartupService() {
        this.webClient = WebClient.builder().baseUrl(URI).build();
    }

    @EventListener(ApplicationReadyEvent.class)
        public void runAfterStartup() {
            BidderRegisterDTO bidderRegisterDTO = new BidderRegisterDTO(bidderID,8000);
           webClient.post().uri("/api/v1/register/bidder")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .body(BodyInserters.fromValue(bidderRegisterDTO)).exchange().block();
        }
}
