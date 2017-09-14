package myown.searchmovies;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Netaq on 9/14/2017.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

    }
}
