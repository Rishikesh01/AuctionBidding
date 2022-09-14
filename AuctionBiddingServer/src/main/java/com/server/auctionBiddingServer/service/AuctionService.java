package com.server.auctionBiddingServer.service;

import com.auction.shared.dto.BidDTO;
import com.auction.shared.dto.BidderRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


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
        List<BidDTO> bids = new ArrayList<>();
        storageFacade.getValues().stream().map(this::makeQuery).filter(Objects::nonNull).forEach(bids::add);
        if (bids.size() != 0) {
            bids.sort(Comparator.comparingInt((BidDTO::getPrice)).reversed());
            return Optional.of(bids.get(0));
        }
        return Optional.empty();
    }

    private BidDTO makeQuery(String uri) {
        try {
            return restTemplate.getForEntity(uri, BidDTO.class).getBody();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addBidder(BidderRegisterDTO bidderRegisterDTO) {
        storageFacade.registerBidder(bidderRegisterDTO);
    }

    public Map<BidderRegisterDTO, String> getAllRegisteredBidders() {
        return storageFacade.getStorage();
    }
}
