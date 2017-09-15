package myown.searchmovies.network.api.interfaces;

import myown.searchmovies.network.models.MovieDetailsResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface MovieDetailsResultListener {

    void onMovieDetailsResult(MovieDetailsResponse movieDetailsResponse);
}
