package com.hardToFind.configuration;

/**
 * Created by zdrillings on 3/22/17.
 */
public class EbayConfiguration {

    private String ebayAppId;
    private String ebayDevId;
    private String ebayCertId;
    private String ebayHost;
    private String ebayFindingHost;
    private String ebayToken;

    public EbayConfiguration(String ebayToken,
                             String ebayHost,
                             String ebayFindingHost,
                             String ebayCertId,
                             String ebayDevId,
                             String ebayAppId){
        this.ebayAppId = ebayAppId;
        this.ebayCertId = ebayCertId;
        this.ebayDevId = ebayDevId;
        this.ebayHost = ebayHost;
        this.ebayFindingHost = ebayFindingHost;
        this.ebayToken = ebayToken;
    }

    public String getEbayAppId() {
        return ebayAppId;
    }

    public String getEbayDevId() {
        return ebayDevId;
    }

    public String getEbayCertId() {
        return ebayCertId;
    }

    public String getEbayHost() {
        return ebayHost;
    }

    public String getEbayFindingHost() {
        return ebayFindingHost;
    }

    public String getEbayToken() {
        return ebayToken;
    }
}
