package com.client.biddingclient.service;

import com.client.biddingclient.config.BidderClientConfiguration;
import com.client.biddingclient.controller.BidController;
import com.client.biddingclient.dto.BidderRegisterDTO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class StartupService {

    private final WebClient webClient;

    private final BidderClientConfiguration clientConfiguration;
    private final BidController bidController;
    @Value("${name}")
    private String bidderID;
    @Value("${port}")
    private String port;

    @EventListener(ApplicationReadyEvent.class)
    public void  register() {
        BidderRegisterDTO bidderRegisterDTO = new BidderRegisterDTO(bidderID, port);
        webClient.post().uri(clientConfiguration.getEndpoint())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bidderRegisterDTO)).exchange().block();

        bidController.makeBid();
    }
}
