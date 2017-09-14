package myown.searchmovies.network.bals.interfaces;

import myown.searchmovies.network.models.MoviesResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface ResultsListener {

    void onResultsFound(MoviesResponse moviesResponse);
}
