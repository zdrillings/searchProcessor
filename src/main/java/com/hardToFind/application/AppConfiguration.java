package com.hardToFind.application;

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

    private String ebayAppId;
    private String ebayDevId;
    private String ebayCertId;
    private String ebayHost;

    private String ebayToken;

    public AppConfiguration(String fileLocation) throws Exception {
        //URL configFile = AppConfiguration.class.getClass().getResource("config/app_config.yml");
        File configFile = new File(fileLocation);
            InputStream configFileStream = new FileInputStream(configFile);
        Properties p = new Properties();
            p.load(configFileStream);
            configFileStream.close();

            ebayAppId = (String) p.get("ebayAppId");
            ebayDevId = (String) p.get("ebayDevId");
            ebayCertId = (String) p.get("ebayCertId");
            ebayToken = (String) p.get("ebayToken");
            ebayHost = (String) p.get("ebayHost");
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

    public String getEbayToken() {
        return ebayToken;
    }
}
