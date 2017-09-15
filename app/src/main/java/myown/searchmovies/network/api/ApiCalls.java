package myown.searchmovies.network.api;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import myown.searchmovies.network.api.interfaces.MovieDetailsResultListener;
import myown.searchmovies.network.api.interfaces.KeywordsSearchResultsListener;
import myown.searchmovies.network.bals.MovieSearchBAL;
import myown.searchmovies.network.bals.MoviesDetailsBAL;
import myown.searchmovies.network.bals.KeywordsSearchBAL;
import myown.searchmovies.network.bals.PopularMoviesBAL;
import myown.searchmovies.network.bals.TopRatedMoviesBAL;
import myown.searchmovies.network.bals.interfaces.MoviesDetailsFetchListener;
import myown.searchmovies.network.bals.interfaces.MoviesFetchListener;
import myown.searchmovies.network.api.interfaces.MoviesResultsListener;
import myown.searchmovies.network.bals.interfaces.KeywordsSearchFetchListener;
import myown.searchmovies.network.models.MovieDetailsResponse;
import myown.searchmovies.network.models.MoviesResponse;
import myown.searchmovies.network.models.KeywordsSearchResponse;
import myown.searchmovies.utils.Utils;

/**
 * Created by Netaq on 9/14/2017.
 */

public class ApiCalls {

    //Calling the APi to get popular movies
    public static void getPopularMovies(final Context context, int page, final SwipeRefreshLayout swipeRefresh, final MoviesResultsListener mListener){

        swipeRefresh.setRefreshing(true);

        PopularMoviesBAL.getPopularMovies(page , new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                swipeRefresh.setRefreshing(false);

                mListener.onResultsFound(response);

            }

            @Override
            public void onMoviesNotFetched() {
                Utils.onResponseIssueException(context);
            }

            @Override
            public void onNetworkFailure() {
                Utils.noInternetException(context);
            }

            @Override
            public void onResponseIssue() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onRequestTimeout() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onException(String errorMessage) {
                Utils.unableToFetchDataException(context);

            }
        });


    }

    //Calling the api to get top rated movies
    public static void getTopRatedMovies(final Context context, int page, final SwipeRefreshLayout swipeRefresh, final MoviesResultsListener mListener){

        swipeRefresh.setRefreshing(true);

        TopRatedMoviesBAL.getPopularMovies(page, new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {

                swipeRefresh.setRefreshing(false);

                mListener.onResultsFound(response);

            }

            @Override
            public void onMoviesNotFetched() {
                Utils.onResponseIssueException(context);
            }

            @Override
            public void onNetworkFailure() {
                Utils.noInternetException(context);
            }

            @Override
            public void onResponseIssue() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onRequestTimeout() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onException(String errorMessage) {
                Utils.unableToFetchDataException(context);

            }
        });

    }

    //Calling the api to get the movie details
    public static void getMovieDetails(final Context context, String movieId, final MovieDetailsResultListener mListener){

        MoviesDetailsBAL.getMoviesDetails(movieId, new MoviesDetailsFetchListener() {
            @Override
            public void onMoviesDetailsFetched(MovieDetailsResponse response) {

                mListener.onMovieDetailsResult(response);

            }

            @Override
            public void onMoviesDetailsNotFetched() {
                Utils.onResponseIssueException(context);
            }

            @Override
            public void onNetworkFailure() {
                Utils.noInternetException(context);
            }

            @Override
            public void onResponseIssue() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onRequestTimeout() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onException(String errorMessage) {
                Utils.unableToFetchDataException(context);
            }
        });
    }

    //Calling the search keywords api
    public static void searchKeywords(String query, final KeywordsSearchResultsListener mListener){

        KeywordsSearchBAL.searchKeywords(query, new KeywordsSearchFetchListener() {
            @Override
            public void onKeywordsSearchFetched(KeywordsSearchResponse response) {
                mListener.onResultsFound(response);
            }

            @Override
            public void onKeywordsSearchNotFetched() {

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

    //Calling the sear movie api
    public static void searchMovie(final Context context, String query, final MoviesResultsListener mListener){

        MovieSearchBAL.movieSearch(query, new MoviesFetchListener() {
            @Override
            public void onMoviesFetched(MoviesResponse response) {
                mListener.onResultsFound(response);
            }

            @Override
            public void onMoviesNotFetched() {
                Utils.onResponseIssueException(context);
            }

            @Override
            public void onNetworkFailure() {
                Utils.noInternetException(context);
            }

            @Override
            public void onResponseIssue() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onRequestTimeout() {
                Utils.unableToFetchDataException(context);
            }

            @Override
            public void onException(String errorMessage) {
                Utils.unableToFetchDataException(context);

            }
        });

    }

}
