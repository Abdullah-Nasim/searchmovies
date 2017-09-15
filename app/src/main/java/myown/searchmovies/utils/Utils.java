package myown.searchmovies.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import myown.searchmovies.R;
import myown.searchmovies.network.models.KeywordsSearchResponse;
import myown.searchmovies.network.models.MovieDetailsResponse;

/**
 * Created by Netaq on 9/15/2017.
 */

public class Utils {

    //The following method is responsible to show full screen progress bar
    public static void showFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.VISIBLE);
    }

    //The following methos is responsible to hide full screen progress bar
    public static void hideFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.GONE);
    }

    //The following method is used to process the array for material search view in required format
    public static String[] prepareSearchResultsArray(List<KeywordsSearchResponse.Result> movies){

        String[] resultsArray = new String[movies.size() ];

        for(int i =0; i<movies.size(); i++){
            resultsArray[i] = movies.get(i).getName();
        }

        return resultsArray;

    }

    public static void noInternetException(Context context){
        showAlertDialog(context.getString(R.string.no_internet_messege), context);
    }

    public static void onResponseIssueException(Context context){
        showAlertDialog(context.getString(R.string.response_error_messege), context);
    }

    public static void unableToFetchDataException(Context context){
        showAlertDialog(context.getString(R.string.server_error_messege), context);
    }

    //The following method is used to process genres in specified format
    public static String processGenres (List<MovieDetailsResponse.Genre> genres){

        String genresString = "";

        for(int i=0; i < genres.size(); i++){
            genresString = genresString + genres.get(i).getName()+ " ";
        }

        return genresString;
    }

    //The following method is used to build and show the alert dialog
    public static void showAlertDialog(String msg, Context context){
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(R.string.error)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    //The following method is used to force crash the application in order to text crashlytics
    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }
}
