package com.demo.auctionbidding.service;

import com.demo.auctionbidding.dto.BidderRegisterDTO;
import com.demo.auctionbidding.dto.BiddingDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */

@Service
public class AuctionService {
    private static final String QUERY_BID_ENDPOINT = "/v1/bid";
    private static final String URL = "http://localhost:";
    private final WebClient webClient;
    private final HashMap<BidderRegisterDTO, String> bidderRegisterDTOSet = new HashMap<>();

    public AuctionService() {
        this.webClient = WebClient.builder().build();
    }

    public void setBidderRegisterDTOMap(BidderRegisterDTO bidderRegisterDTO) {
        System.out.println(bidderRegisterDTO);
        bidderRegisterDTOSet.put(bidderRegisterDTO, URL + bidderRegisterDTO.getPort() + QUERY_BID_ENDPOINT);
    }

    public BiddingDTO getBestBid(String auction_ID) {
        if (bidderRegisterDTOSet.size() != 0) {
            List<BiddingDTO> bids = bidderRegisterDTOSet
                    .values().stream()
                    .parallel()
                    .map(this::makeQuery)
                    .map(Mono::block)
                    .sorted(Comparator.comparingInt((BiddingDTO biddingDTO) -> biddingDTO != null ? biddingDTO.getPrice() : 0).reversed())
                    .collect(Collectors.toList());

            return bids.get(0);
        }
        return null;
    }

    private Mono<BiddingDTO> makeQuery(String uri) {
        return webClient
                .get().uri(uri).retrieve().bodyToMono(BiddingDTO.class).timeout(Duration.ofMillis(200));
    }
}
