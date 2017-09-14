package myown.searchmovies.network.api;

import android.support.v4.widget.SwipeRefreshLayout;

import myown.searchmovies.network.bals.PopularMoviesBAL;
import myown.searchmovies.network.bals.TopRatedMoviesBAL;
import myown.searchmovies.network.bals.interfaces.MoviesFetchListener;
import myown.searchmovies.network.bals.interfaces.ResultsListener;
import myown.searchmovies.network.models.MoviesResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public class ApiCalls {


    public static void getPopularMovies(int page, final SwipeRefreshLayout swipeRefresh, final ResultsListener mListener){

        swipeRefresh.setRefreshing(true);

        PopularMoviesBAL.getPopularMovies(page , new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                swipeRefresh.setRefreshing(false);

                mListener.onResultsFound(response);

            }

            @Override
            public void onMoviesNotFetched() {

            }

            @Override
            public void onNetworkFailure() {

            }

            @Override
            public void onResponseIssue() {

            }

            @Override
            public void onRequestTimeout() {

            }

            @Override
            public void onException(String errorMessage) {

            }
        });


    }

    public static void getTopRatedMovies(int page, final SwipeRefreshLayout swipeRefresh, final ResultsListener mListener){

        swipeRefresh.setRefreshing(true);

        TopRatedMoviesBAL.getPopularMovies(page, new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                swipeRefresh.setRefreshing(false);

                mListener.onResultsFound(response);

            }

            @Override
            public void onMoviesNotFetched() {

            }

            @Override
            public void onNetworkFailure() {

            }

            @Override
            public void onResponseIssue() {

            }

            @Override
            public void onRequestTimeout() {

            }

            @Override
            public void onException(String errorMessage) {

            }
        });

    }

}
