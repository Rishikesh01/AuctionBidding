# Ad Request auction system
- It takes an ad request and Query's clients to bid on it.
## Modules description
### 1.AuctionBiddingServer: 
-It has Auctioneer server which makes calls to clients to bid on the ad request in 200ms.
 ### 2.BiddingClient
- It makes bid to Auctioneer in any delay of 10ms to 500ms.
### 3.SharedDto
- It has shared Domain related pojos.

## Json format accepted and exchanged by client and servers

- Client Registration JSON
    ```
   "bidder_id":"......",
   "bidder_address":".....",
   "port":"...."
    ``` 
- Client Bid JSON
    ```
  "bidder_id":"....",
  "price":"....."
    ```
- Auctioneer ad request format 
  ```
  "auction_ID":"....."
  ```
## Required environment variables list
- SERVER_URL_PREFIX
  ``` 
  example SERVER_URL_PREFIX=http://localhost:8080
  ```
- BIDDER_URL_PREFIX

  ```
  example BIDDER_URL_PREFIX=http://localhost
  ```
## Required commandline args for client to run

- port
  ``` 
  example --port=8000 
  ```
- delay
  ``` 
  example: --delay=100
  ```
- name
  ```
  example: --name=tim
  ```
