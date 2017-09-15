package myown.searchmovies.network.bals.interfaces;


import myown.searchmovies.network.models.KeywordsSearchResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface KeywordsSearchFetchListener extends GeneralNetworkListener{

    void onKeywordsSearchFetched(KeywordsSearchResponse response);
    void onKeywordsSearchNotFetched();
}
