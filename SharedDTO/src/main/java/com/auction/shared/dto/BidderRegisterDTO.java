package com.auction.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@RequiredArgsConstructor
@Data
public class BidderRegisterDTO {
    @JsonProperty(value = "bidder_id")
    private final String bidderId;
    @JsonProperty(value = "bidder_address")
    private final String bidderAddress;
    private final String port;
}
