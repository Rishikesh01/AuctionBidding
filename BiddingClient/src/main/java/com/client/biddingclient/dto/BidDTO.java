package com.client.biddingclient.dto;

import lombok.Data;

/**
 * @author Rishikesh
 * @project AuctionBidding
 */
@Data
public class BidDTO {

    // todo create a new module - shared-dto
    // and move there your dto classes which you will use in different modules.

    // client
    // service/server
    // shared-dto

    private String bidder_id;
    private int price;
}
