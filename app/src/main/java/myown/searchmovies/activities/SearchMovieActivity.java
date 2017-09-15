package myown.searchmovies.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.searchmovies.Constants;
import myown.searchmovies.R;
import myown.searchmovies.network.api.ApiCalls;
import myown.searchmovies.network.api.interfaces.MovieDetailsResultListener;
import myown.searchmovies.network.api.interfaces.KeywordsSearchResultsListener;
import myown.searchmovies.network.api.interfaces.MoviesResultsListener;
import myown.searchmovies.network.models.MovieDetailsResponse;
import myown.searchmovies.network.models.KeywordsSearchResponse;
import myown.searchmovies.network.models.MoviesResponse;
import myown.searchmovies.utils.Utils;

/**
 * Created by Netaq on 9/15/2017.
 */

public class SearchMovieActivity extends AppCompatActivity {

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.movie_image)
    SimpleDraweeView movieImage;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.search_result_layout)
    RelativeLayout searchResultLayout;

    @BindView(R.id.progress_layout)
    FrameLayout progressLayout;

    private KeywordsSearchResponse searchResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        //Setting up the action bar
        setSupportActionBar(toolbar);

        //This function is responsible for setting up the functionality of searchView
        setupMaterialSearchView();

    }

    private void setupMaterialSearchView() {

        searchView.setVoiceSearch(false);
        searchView.setEllipsize(true);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //Show full screen progress bar
                Utils.showFullScreenProgress(progressLayout);

                //Call search movie api and pass the respective parameters
                ApiCalls.searchMovie(SearchMovieActivity.this, query, new MoviesResultsListener() {
                    @Override
                    public void onResultsFound(MoviesResponse moviesResponse) {

                        //Setting up the views
                        movieImage.setImageURI(Uri.parse(Constants.Image_Base_URL + moviesResponse.getResults().get(0).getPoster_path()));
                        movieTitle.setText(moviesResponse.getResults().get(0).getTitle());

                        //Make the hidden views visible
                        searchResultLayout.setVisibility(View.VISIBLE);

                        //Hide the full screen progress bar
                        Utils.hideFullScreenProgress(progressLayout);
                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.length() >= 3){
                    ApiCalls.searchKeywords(newText, new KeywordsSearchResultsListener() {
                        @Override
                        public void onResultsFound(KeywordsSearchResponse keywordsSearchResponse) {

                            searchResponse = keywordsSearchResponse;

                            //Adding the response of keywords search api to the search view results
                            searchView.setSuggestions(Utils.prepareSearchResultsArray(keywordsSearchResponse.getResults()));

                        }
                    });
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        //Setting up the menu options
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }
}
