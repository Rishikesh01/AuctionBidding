package com.server.auctionBiddingServer.service;

import com.auction.shared.dto.BidderRegisterDTO;

import java.util.Collection;
import java.util.Map;

public interface BidderStorageFacade {
    void registerBidder(BidderRegisterDTO bidderRegisterDTO);

    Collection<String> getValues();

    Collection<BidderRegisterDTO> getKeys();

    Map<BidderRegisterDTO, String> getStorage();

    boolean isEmpty();
}
