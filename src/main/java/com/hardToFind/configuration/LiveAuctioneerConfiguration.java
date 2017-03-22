package com.hardToFind.configuration;

/**
 * Created by zdrillings on 3/22/17.
 */
public class LiveAuctioneerConfiguration {

    private String host;

    public LiveAuctioneerConfiguration(String host){
        this.host = host;
    }

    public String getHost(){
        return this.host;
    }
}
