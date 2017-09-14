package myown.searchmovies.network.bals.interfaces;

/**
 * Created by Netaq on 9/14/2017.
 */

public interface GeneralNetworkListener {

    void onNetworkFailure();
    void onResponseIssue();
    void onRequestTimeout();
    void onException(String errorMessage);

}
