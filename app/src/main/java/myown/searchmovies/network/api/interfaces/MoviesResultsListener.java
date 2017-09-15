package myown.searchmovies.network.api.interfaces;

import myown.searchmovies.network.models.MoviesResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface MoviesResultsListener {

    void onResultsFound(MoviesResponse moviesResponse);
}
