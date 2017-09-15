package myown.searchmovies.utils;

import android.content.Context;
import android.content.Intent;

import myown.searchmovies.activities.MainActivity;
import myown.searchmovies.activities.MovieDetailsActivity;
import myown.searchmovies.network.models.MoviesResponse;

/**
 * Created by Netaq on 9/14/2017.
 */

public class NavigationController {

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startMovieDetailsActivity(Context context, Integer movieId){
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("Movie_Id", movieId);
        context.startActivity(intent);

    }
}
