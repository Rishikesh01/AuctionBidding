package com.demo.auctionbidding.controller;

import com.demo.auctionbidding.dto.AdToAuctionDTO;
import com.demo.auctionbidding.dto.BidderRegisterDTO;
import com.demo.auctionbidding.dto.BiddingDTO;
import com.demo.auctionbidding.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@RestController
// Base url starts with http://localhost:8080/api/v1
@RequestMapping("/v1")
public class AuctioneerController {

    private final AuctionService auctionService;

    @PostMapping("/register/bidder")
    public ResponseEntity<HttpStatus> registerBidder(@RequestBody BidderRegisterDTO bidderRegisterDTO) {
        auctionService.setBidderRegisterDTOMap(bidderRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/auction")
    public ResponseEntity<Object> auction(@RequestBody AdToAuctionDTO adToAuctionDTO) {
        BiddingDTO biddingDTO = auctionService.getBestBid(adToAuctionDTO.getAuction_ID());
        if (biddingDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No clients found");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(biddingDTO);
    }


}
