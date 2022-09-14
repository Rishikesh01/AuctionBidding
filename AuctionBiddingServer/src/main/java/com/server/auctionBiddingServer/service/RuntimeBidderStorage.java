package com.server.auctionBiddingServer.service;

import com.auction.shared.dto.BidderRegisterDTO;
import com.server.auctionBiddingServer.config.ServerConfigurationProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class RuntimeBidderStorage implements BidderStorageFacade {

    private final Map<BidderRegisterDTO, String> store = new HashMap<>();

    private final ServerConfigurationProperties properties;

    @Override
    public void registerBidder(BidderRegisterDTO bidderRegisterDTO) {
        store.put(bidderRegisterDTO, properties.getUrl() + bidderRegisterDTO.getPort() + properties.getEndpoint());
    }

    @Override
    public Collection<String> getValues() {
        return store.values();
    }

    @Override
    public Collection<BidderRegisterDTO> getKeys() {
        return store.keySet();
    }

    @Override
    public Map<BidderRegisterDTO, String> getStorage() {
        return store;
    }

    @Override
    public boolean isEmpty() {
        return store.size() == 0;
    }
}
