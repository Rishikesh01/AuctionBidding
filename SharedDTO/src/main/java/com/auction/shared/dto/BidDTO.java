package com.auction.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Data
public class BidDTO {
    @JsonProperty(value = "bidder_id")
    private String bidderId;
    private int price;
}
