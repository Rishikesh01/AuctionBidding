package com.rishikesh.auctionbidding.controller;

import com.rishikesh.auctionbidding.dto.AdToAuctionDTO;
import com.rishikesh.auctionbidding.dto.BidderRegisterDTO;
import com.rishikesh.auctionbidding.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auctioneer")
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
    public ResponseEntity<Object> auction(@RequestBody AdToAuctionDTO adToAuctionDTO) {
        if (adToAuctionDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please send auction id");
        }
        if (!auctionService.hasRegisteredBidder()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please send auction id");
        }

        return auctionService.getBestBid()
                .map(biddingDTO -> ResponseEntity.status(HttpStatus.ACCEPTED).body(biddingDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No clients found"));
    }

    @GetMapping("/end-points/list")
    public ResponseEntity<Object> listOfEndPoints() {
        return ResponseEntity.ok(auctionService.getAllRegisteredBidders());
    }

}
