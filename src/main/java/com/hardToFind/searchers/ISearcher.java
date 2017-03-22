package com.hardToFind.searchers;

import com.hardToFind.Models.SearchResultItem;

import java.util.List;

/**
 * Created by zdrillings on 3/22/17.
 */
public interface ISearcher {

    List<SearchResultItem> search(String query);
}
