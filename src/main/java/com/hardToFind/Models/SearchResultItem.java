package com.hardToFind.Models;

/**
 * Created by zdrillings on 3/22/17.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResultItem {

    private String link = null;

    private String website = null;

    private float price = 0;

    public SearchResultItem() {

    }

    public SearchResultItem(String link, String website, float price) {
        this(link, website);
        this.price = price;
    }

    public SearchResultItem(String link, String website) {
        this.link = link;
        this.website = website;
    }

    public String toString(){
        String ret = "Link: " + this.getLink();
        ret = ret + " -- Site: " + this.getWebsite();
        ret = ret + " -- Price: " + this.getPrice();
        return ret;
    }

    @JsonProperty
    public String getLink() {
        return link;
    }

    @JsonProperty
    public String getWebsite() {
        return website;
    }

    @JsonProperty
    public float getPrice() {
        return price;
    }

}