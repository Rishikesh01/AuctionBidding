package com.server.auctionBiddingServer.controller;

import com.auction.shared.dto.BidderRegisterDTO;
import com.server.auctionBiddingServer.dto.AdToAuctionDTO;
import com.server.auctionBiddingServer.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Slf4j
public class AuctioneerController {

    private final AuctionService auctionService;

    @PostMapping("/register/bidder")
    public ResponseEntity<HttpStatus> registerBidder(@RequestBody BidderRegisterDTO bidderRegisterDTO) {
        log.info("Register bidder request: {}", bidderRegisterDTO);
        auctionService.addBidder(bidderRegisterDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/auction")
    public ResponseEntity<Mono<Object>> auction(@RequestBody AdToAuctionDTO adToAuctionDTO) {
        if (adToAuctionDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Mono.just("please send auction id1"));
        }

        return auctionService.getBestBid()
                .map(biddingDTO -> ResponseEntity.status(HttpStatus.ACCEPTED).body(Mono.just(biddingDTO)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Mono.just("No clients found")));
    }

    @GetMapping("/end-points/list")
    public ResponseEntity<Object> listOfEndPoints() {
        return ResponseEntity.ok(auctionService.getAllRegisteredBidders());
    }

}
