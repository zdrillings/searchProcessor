package com.hardToFind.connectors;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;

import java.io.IOException;
import java.util.Calendar;

/**
 * Created by zdrillings on 3/22/17.
 */
public class EbayConnector {

        private String token;
        private String host;

    public EbayConnector(String token, String host) {
        this.token = token;
        this.host = host;
    }

    public String callEbay() {
        try {

            // Instantiate  ApiContext and initialize with token and Trading API URL
            ApiContext apiContext = getApiContext();

            //Create call object and execute the call
            GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
            Calendar cal = apiCall.geteBayOfficialTime();

            //Handle the result returned
            System.out.println("Official eBay Time : " + cal.getTime().toString());
            return cal.getTime().toString();
        } catch (Exception e) {
            System.out.println("Fail to get eBay official time.");
            e.printStackTrace();
        }
        return null;
    }

    // Initializes ApiContext with token and eBay API server URL
    private ApiContext getApiContext() throws IOException {

        String input;
        ApiContext apiContext = new ApiContext();

        //set Api Token to access eBay Api Server
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(this.token);

        //set Api Server Url
        apiContext.setApiServerUrl(this.host);

        return apiContext;

    }

}

