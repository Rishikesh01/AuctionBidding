package com.client.biddingclient.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@Data
public class BidderRegisterDTO {
    private final String bidder_id;
    private final int port;
}
