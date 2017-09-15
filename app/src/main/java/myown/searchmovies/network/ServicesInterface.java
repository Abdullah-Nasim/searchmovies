package myown.searchmovies.network;

import myown.searchmovies.network.api.Endpoints;
import myown.searchmovies.network.models.MovieDetailsResponse;
import myown.searchmovies.network.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Netaq on 9/14/2017.
 */


public interface ServicesInterface {

    @GET(Endpoints.Popular_Endpoint)
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String api_key,@Query("language") String language, @Query("page") int page);

    @GET(Endpoints.Top_Rated_Endpoint)
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String api_key,@Query("language") String language, @Query("page") int page);

    @GET("{movie_id}")
    Call<MovieDetailsResponse> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String api_key, @Query("language") String language);

}
