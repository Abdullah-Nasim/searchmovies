package myown.searchmovies.network;



import myown.searchmovies.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Netaq on 9/14/2017.
 */

public class RestClient {

    private static ServicesInterface servicesInterface;
    private static Retrofit retrofit;

    static {

        //Configuring the Rest Client
        setUpRestClient();
    }

    private static void setUpRestClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesInterface = retrofit.create(ServicesInterface.class);


    }

    public static ServicesInterface getAdapter(){
        return servicesInterface;
    }

}
