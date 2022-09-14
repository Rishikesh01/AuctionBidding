package com.client.biddingclient.service;

import com.auction.shared.dto.BidDTO;
import com.auction.shared.dto.BidderRegisterDTO;
import com.client.biddingclient.config.BidderClientConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Component
@RequiredArgsConstructor
public class StartupService {

    private final WebClient webClient;
    private final BidderClientConfiguration clientConfiguration;

    @Value("${name}")
    private String bidderID;
    @Value("${port}")
    private String port;
    @Value("${delay}")
    private String delay;

    @EventListener(ApplicationReadyEvent.class)
    public void register() {
        BidderRegisterDTO bidderRegisterDTO = new BidderRegisterDTO(bidderID, clientConfiguration.getBidderURL(), port);
        webClient.post().uri(clientConfiguration.getEndpoint())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(bidderRegisterDTO)).exchange().block();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void warmUp() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(clientConfiguration.getBidderURL() + port + "/v1/bid", BidDTO.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void checkDelayLimit() {
        long delay = Long.parseLong(this.delay);
        if (delay > 500)
            throw new RuntimeException("Greater than 500mil sec");
        if (delay <= 0)
            throw new RuntimeException("delay cannot be less than or equal to zero");
    }


}
