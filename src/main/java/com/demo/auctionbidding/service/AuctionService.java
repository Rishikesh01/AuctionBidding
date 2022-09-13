package com.demo.auctionbidding.service;

import com.demo.auctionbidding.dto.BidderRegisterDTO;
import com.demo.auctionbidding.dto.BiddingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */

@Service
@RequiredArgsConstructor
public class AuctionService {
    private static final String QUERY_BID_ENDPOINT = "/v1/bid";
    private static final String URL = "http://localhost:";
    private final RestTemplate restTemplate;
    private final HashMap<BidderRegisterDTO, String> bidderRegisterDTOSet = new HashMap<>();


    public void setBidderRegisterDTOMap(BidderRegisterDTO bidderRegisterDTO) {
        bidderRegisterDTOSet.put(bidderRegisterDTO, URL + bidderRegisterDTO.getPort() + QUERY_BID_ENDPOINT);
    }

    public boolean areRegisteredBiddersZero() {
        return bidderRegisterDTOSet.size() == 0;
    }

    public BiddingDTO getBestBid() {
        List<BiddingDTO> bids = new ArrayList<>();
        for (String url : bidderRegisterDTOSet.values()) {
            BiddingDTO biddingDTO = makeQuery(url);
            if (biddingDTO != null)
                bids.add(biddingDTO);
        }
        if (bids.size() != 0) {
            bids.sort(Comparator.comparingInt((BiddingDTO::getPrice)).reversed());
            return bids.get(0);
        }
        return null;
    }

    private BiddingDTO makeQuery(String uri) {
        try {
            return restTemplate.getForEntity(uri, BiddingDTO.class).getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }
}
