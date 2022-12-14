package com.client.biddingclient.controller;

import com.auction.shared.dto.BidDTO;
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
        Mono<BidDTO> bidDTO = Mono.just(biddingService.createBid());
        try {
            Thread.sleep(Long.parseLong(delay));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(bidDTO);
    }
}
