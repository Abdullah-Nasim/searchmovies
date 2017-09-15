package myown.searchmovies.network.api.interfaces;

import myown.searchmovies.network.models.KeywordsSearchResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface KeywordsSearchResultsListener {

    void onResultsFound(KeywordsSearchResponse keywordsSearchResponse);
}
