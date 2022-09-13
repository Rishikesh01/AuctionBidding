package com.rishikesh.auctionbidding.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Data
public class BidderRegisterDTO {
    @JsonProperty("bidder_id")
    private String bidderId;
    private int port;
}
