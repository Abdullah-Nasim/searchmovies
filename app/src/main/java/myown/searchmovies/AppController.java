package myown.searchmovies;

import android.app.Application;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Netaq on 9/14/2017.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing fabric.io crashlytics
        Fabric.with(this, new Crashlytics());

        //Initializing fresco
        Fresco.initialize(this);

    }

}
