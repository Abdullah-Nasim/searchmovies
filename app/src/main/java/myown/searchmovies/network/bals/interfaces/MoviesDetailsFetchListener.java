package myown.searchmovies.network.bals.interfaces;


import myown.searchmovies.network.models.MovieDetailsResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface MoviesDetailsFetchListener extends GeneralNetworkListener{

    void onMoviesDetailsFetched(MovieDetailsResponse response);
    void onMoviesDetailsNotFetched();
}
