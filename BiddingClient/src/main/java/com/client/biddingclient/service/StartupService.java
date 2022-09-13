package com.client.biddingclient.service;

import com.client.biddingclient.controller.BidController;
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
    private static final String URI = "http://localhost:8080";
    private static final String END_POINT = "/api/v1/register/bidder";
    private final WebClient webClient;
    private final BidController bidController;
    @Value("${name}")
    private String bidderID;
    @Value("${port}")
    private String port;

    public StartupService(BidController bidController) {
        this.bidController = bidController;
        this.webClient = WebClient.builder().baseUrl(URI).build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        BidderRegisterDTO bidderRegisterDTO = new BidderRegisterDTO(bidderID, port);
        System.out.println(bidderRegisterDTO);
        webClient.post().uri(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bidderRegisterDTO)).exchange().block();
        bidController.makeBid();
    }
}
