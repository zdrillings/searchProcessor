package com.hardToFind.connectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardToFind.Models.LiveAuctioneerParameter;
import com.hardToFind.Models.SearchResultItem;
import com.hardToFind.configuration.LiveAuctioneerConfiguration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zdrillings on 3/22/17.
 */
public class LiveAuctioneerConnector {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LiveAuctioneerConnector.class);

    private String host;

    public LiveAuctioneerConnector(LiveAuctioneerConfiguration configuration){
        this.host = configuration.getHost();
    }

    public List<SearchResultItem> search(String query){
        LiveAuctioneerParameter liveAuctioneerParameter = new LiveAuctioneerParameter(query);
        String parameter;
        try {
            parameter = (new ObjectMapper()).writeValueAsString(liveAuctioneerParameter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("Failed to parse parameters");
            return null;
        }
        String url = this.host + "?parameters=" + parameter;
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Failed to connect to live auctioneers");
            return null;
        }
        Elements elements = doc.getElementsByClass("cards___1r0Wa item-card___3_cUz");
        List<SearchResultItem> searchResultItems = elements.stream()
                                                        .map(element -> {
                                                            Elements elems = element.getElementsByClass("item-title___3zS_6");
                                                            String link = elems.first().attr("abs:href");
                                                            String title = elems.first().getElementsByTag("span").html();
                                                            elems = element.getElementsByClass("item-current-bid___j1nna");
                                                            SearchResultItem searchResultItem = new SearchResultItem(link, "liveAuctioneers");
                                                            try {
                                                                String price = elems.first().getElementsByTag("span").first().text();
                                                                price = price.replace("$", "");
                                                                price = price.replace(",", "");
                                                                searchResultItem.setPrice(Double.parseDouble(price));
                                                            }catch(Exception ex){
                                                                LOGGER.error("Could not parse price");
                                                            }
                                                            searchResultItem.setTitle(title);

                                                            return searchResultItem;
                                                         })
                                                        .collect(Collectors.toList());


        return searchResultItems;
    }
}
