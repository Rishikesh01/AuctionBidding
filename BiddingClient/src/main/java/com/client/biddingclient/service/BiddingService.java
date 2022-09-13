package com.client.biddingclient.service;

import com.client.biddingclient.dto.BidDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */

@Service
public class BiddingService {
    @Value("${name}")
    private String bidderId;

    public BidDTO createBid() {
        BidDTO bidDTO = new BidDTO();
        bidDTO.setBidder_id(bidderId);
        bidDTO.setPrice(bidPriceGenerator());
        return bidDTO;
    }

    private int bidPriceGenerator() {
        Random random = new Random();
        return random.nextInt((2560 - 1400) + 1) + 1400;
    }
}
