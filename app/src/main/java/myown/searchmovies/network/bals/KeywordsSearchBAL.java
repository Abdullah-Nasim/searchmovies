package myown.searchmovies.network.bals;


import myown.searchmovies.Constants;
import myown.searchmovies.network.RestClient;
import myown.searchmovies.network.bals.interfaces.KeywordsSearchFetchListener;
import myown.searchmovies.network.models.KeywordsSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Netaq on 9/14/2017.
 */

public class KeywordsSearchBAL {

    public static void searchKeywords(String query, final KeywordsSearchFetchListener listener){

        Call<KeywordsSearchResponse> call = RestClient.getAdapter().searchKeywords(Constants.Api_Key, query, 1);

        call.enqueue(new Callback<KeywordsSearchResponse>() {
            @Override
            public void onResponse(Call<KeywordsSearchResponse> call, Response<KeywordsSearchResponse> response) {

                //Check if response is successful
                if(response.isSuccessful()) {

                    //Check if response dose not contain empty body
                    if (response.body().getResults().size() != 0) {

                        //keywords successfully fetched
                        listener.onKeywordsSearchFetched(response.body());

                    } else {
                        listener.onKeywordsSearchNotFetched();
                    }

                } else {
                    listener.onResponseIssue();
                }

            }

            @Override
            public void onFailure(Call<KeywordsSearchResponse> call, Throwable t) {

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
