package com.hardToFind.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zdrillings on 3/22/17.
 */
public class AppConfiguration {

    public static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

    private EbayConfiguration ebayConfiguration;
    private LiveAuctioneerConfiguration liveAuctioneerConfiguration;

    public AppConfiguration(String fileLocation) throws Exception {
        //URL configFile = AppConfiguration.class.getClass().getResource("config/app_config.yml");
        File configFile = new File(fileLocation);
            InputStream configFileStream = new FileInputStream(configFile);
        Properties p = new Properties();
            p.load(configFileStream);
            configFileStream.close();

            ebayConfiguration = new EbayConfiguration((String) p.get("ebayToken"),
                                                        (String) p.get("ebayHost"),
                                                        (String) p.get("ebayFindingHost"),
                                                        (String) p.get("ebayCertId"),
                                                        (String) p.get("ebayDevId"),
                                                        (String) p.get("ebayAppId"));

            liveAuctioneerConfiguration = new LiveAuctioneerConfiguration((String) p.get("liveAuctioneerHost"));

    }

    public LiveAuctioneerConfiguration getLiveAuctioneerConfiguration(){
        return  this.liveAuctioneerConfiguration;
    }

    public EbayConfiguration getEbayConfiguration(){
        return this.ebayConfiguration;
    }

}
