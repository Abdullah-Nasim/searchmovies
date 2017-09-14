package myown.searchmovies.network.bals;


import myown.searchmovies.Constants;
import myown.searchmovies.network.RestClient;
import myown.searchmovies.network.bals.interfaces.MoviesFetchListener;
import myown.searchmovies.network.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 9/14/2017.
 */

public class PopularMoviesBAL {

    public static void getPopularMovies(int page, final MoviesFetchListener listener){

        Call<MoviesResponse> call = RestClient.getAdapter().getPopularMovies(Constants.Api_Key, "en-US", page);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                //Check if response is successful
                if(response.isSuccessful()) {

                    //Check if response dose not contain empty array
                    if (response.body() != null) {

                        //Popular Movies successfully fetched
                        listener.onMoviesFetched(response.body());
                    } else {
                        listener.onMoviesNotFetched();
                    }

                } else {
                    listener.onResponseIssue();
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                //Check the type of Exception
                if(t instanceof IllegalArgumentException){

                    listener.onResponseIssue();

                } else {
                    listener.onNetworkFailure();
                }

                }
        });
    }
}
