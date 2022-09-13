package com.client.biddingclient.controller;

import com.client.biddingclient.dto.BidDTO;
import com.client.biddingclient.service.BiddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class BidController {
    private final BiddingService biddingService;

    @Value("delay")
    private String delay;

    @GetMapping("/bid")
    public ResponseEntity<BidDTO> makeBid() {
        long delay = Long.parseLong(this.delay);
        if (delay > 500)
            throw new RuntimeException("Greater than 500mil sec");
        if (delay <= 0)
            throw new RuntimeException("delay cannot be less than or equal to zero");

        BidDTO bidDTO = biddingService.createBid();

        long delay_in_nanoseconds = delay * 1000000L;
        long start_time = System.nanoTime();
        while (true) {
            long now = System.nanoTime();
            long time_spent_sleeping_thus_far = now - start_time;
            if (time_spent_sleeping_thus_far >= delay_in_nanoseconds) {
                break;
            }
        }
        return ResponseEntity.ok(bidDTO);
    }
}
