package myown.searchmovies.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.searchmovies.Constants;
import myown.searchmovies.R;
import myown.searchmovies.database.DatabaseHandler;
import myown.searchmovies.database.model.MovieDetails;
import myown.searchmovies.network.api.ApiCalls;
import myown.searchmovies.network.api.interfaces.MovieDetailsResultListener;
import myown.searchmovies.network.models.MovieDetailsResponse;
import myown.searchmovies.utils.Utils;

/**
 * Created by Netaq on 9/14/2017.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_tag_line)
    TextView movieTagLine;

    @BindView(R.id.movie_image)
    SimpleDraweeView movieImage;

    @BindView(R.id.movie_genres)
    TextView movieGenres;

    @BindView(R.id.movie_home_page)
    TextView movieHomePage;

    @BindView(R.id.movie_status)
    TextView movieStatus;

    @BindView(R.id.movie_budget)
    TextView movieBudget;

    @BindView(R.id.movie_popularity)
    TextView moviePopularity;

    @BindView(R.id.movie_overview)
    TextView movieOverview;

    @BindView(R.id.progress_layout)
    FrameLayout progressLayout;

    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        //Initializing the database handler
        db = new DatabaseHandler(this);
        db.getWritableDatabase();

        //Getting the Id of the movie from MainRecyclerAdapter
        Integer movieId = getIntent().getIntExtra("Movie_Id", 0);

        //Check that if the movie information is already stored in the database
        if(db.getMovie(movieId) != null){

            //The movie details are already stored in the database
            MovieDetails movie = db.getMovie(movieId);

            //Setting up the views
            movieTitle.setText(movie.getTitle());
            movieTagLine.setText(movie.getTagline());
            movieStatus.setText(movie.getStatus());
            movieBudget.setText(movie.getBudget().toString());
            movieOverview.setText(movie.getOverview());
            movieHomePage.setText(movie.getHomepage());
            moviePopularity.setText(movie.getPopularity());
            movieImage.setImageURI(Uri.parse(Constants.Image_Base_URL + movie.getPoster_path()));
            movieGenres.setText(movie.getGenres());

        }else{

            //The following method call is responsible for calling the movie details api and setting up the views
            getMovieDetails(movieId);

        }

    }

    private void getMovieDetails(Integer movieId) {

        //Show the full screen progress while the data is fetched
        Utils.showFullScreenProgress(progressLayout);

        ApiCalls.getMovieDetails(movieId.toString(), new MovieDetailsResultListener() {
            @Override
            public void onMovieDetailsResult(MovieDetailsResponse movieDetailsResponse) {

                //Setting up the views
                movieTitle.setText(movieDetailsResponse.getTitle());
                movieTagLine.setText(movieDetailsResponse.getTagline());
                movieStatus.setText(movieDetailsResponse.getStatus());
                movieBudget.setText(movieDetailsResponse.getBudget().toString());
                movieOverview.setText(movieDetailsResponse.getOverview());
                movieHomePage.setText(movieDetailsResponse.getHomepage());
                moviePopularity.setText(String.valueOf(movieDetailsResponse.getPopularity()));
                movieImage.setImageURI(Uri.parse(Constants.Image_Base_URL + movieDetailsResponse.getPoster_path()));
                movieGenres.setText(Utils.processGenres(movieDetailsResponse.getGenres()));

                //Adding the fetched movie in database
                MovieDetails movie = new MovieDetails(movieDetailsResponse.getId(),movieDetailsResponse.getTitle(),movieDetailsResponse.getTagline(),movieDetailsResponse.getStatus(),movieDetailsResponse.getBudget(),Utils.processGenres(movieDetailsResponse.getGenres()),movieDetailsResponse.getHomepage(),movieDetailsResponse.getOverview(),String.valueOf(movieDetailsResponse.getPopularity()),movieDetailsResponse.getPoster_path());
                db.addMovie(movie);

                //Hide the full screen progress because the data is fetched successfully
                Utils.hideFullScreenProgress(progressLayout);


            }
        });

    }
}
