package com.rishikesh.auctionbidding.service;

import com.rishikesh.auctionbidding.dto.BidderRegisterDTO;
import com.rishikesh.auctionbidding.dto.BiddingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final RestTemplate restTemplate;
    private final BidderStorageFacade storageFacade;

    public Optional<Object> getBestBid() {
        List<BiddingDTO> bids = new ArrayList<>();
        storageFacade.getValues().stream().map(this::makeQuery).forEach(bids::add);
        if (bids.size() != 0) {
            bids.sort(Comparator.comparingInt((BiddingDTO::getPrice)).reversed());
            return Optional.of(bids.get(0));
        }
        return Optional.empty();
    }

    private BiddingDTO makeQuery(String uri) {
        try {
            return restTemplate.getForEntity(uri, BiddingDTO.class).getBody();
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public void addBidder(BidderRegisterDTO bidderRegisterDTO) {
        storageFacade.registerBidder(bidderRegisterDTO);
    }

    public boolean hasRegisteredBidder() {
        return !storageFacade.isEmpty();
    }

    public Map<BidderRegisterDTO, String> getAllRegisteredBidders() {
        return storageFacade.getStorage();
    }
}
