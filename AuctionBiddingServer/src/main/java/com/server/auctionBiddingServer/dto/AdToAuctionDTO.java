package com.server.auctionBiddingServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Data
public class AdToAuctionDTO {
    @JsonProperty(value = "auction_ID")
    private String auctionId;
}
