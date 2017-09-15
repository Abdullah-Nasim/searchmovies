package myown.searchmovies.utils;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * Created by Netaq on 9/15/2017.
 */

public class Utils {

    public static void showFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.VISIBLE);
    }

    public static void hideFullScreenProgress(FrameLayout progressLayout){
        progressLayout.setVisibility(View.GONE);
    }
}
