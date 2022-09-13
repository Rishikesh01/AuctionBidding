package com.client.biddingclient.controller;

import com.client.biddingclient.dto.BidDTO;
import com.client.biddingclient.service.BiddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class BidController {
    private final BiddingService biddingService;

    @Value("${delay}")
    private String delay;

    @GetMapping("/bid")
    public ResponseEntity<Mono<BidDTO>> makeBid() {

        // todo has to be moved outside
        long delay = Long.parseLong(this.delay);
        if (delay > 500)
            throw new RuntimeException("Greater than 500mil sec");
        if (delay <= 0)
            throw new RuntimeException("delay cannot be less than or equal to zero");


        Mono<BidDTO> bidDTO = Mono.just(biddingService.createBid());
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(bidDTO);
    }
}
