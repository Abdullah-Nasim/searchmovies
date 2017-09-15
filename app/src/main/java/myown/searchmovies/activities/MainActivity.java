package myown.searchmovies.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.searchmovies.R;
import myown.searchmovies.activities.adapters.MainRecyclerAdapter;
import myown.searchmovies.network.api.ApiCalls;
import myown.searchmovies.network.api.interfaces.MoviesResultsListener;
import myown.searchmovies.network.models.MoviesResponse;
import myown.searchmovies.utils.NavigationController;

/**
 * Created by Netaq on 9/14/2017.
 */

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private int navItemIndex = 0;

    private int currentPage = 1;

    private LinearLayoutManager mLayoutManager;

    private MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Disabling the swipe refresh layout.
        swipeRefresh.setEnabled(false);

        //The following method call is responsible of setting up the toolbar.
        setupToolbar();

        //The following method call is responsible of setting up the navigation drawer.
        setupNavigationDrawer();

        //The following method call is responsible for setting up the recycler view, its adapters and onScrollListeners for pagination
        initMainRecycler();

    }

    private void setupToolbar() {

        toolbar.setTitle(R.string.popular_movies);
        setSupportActionBar(toolbar);

    }

    private void setupNavigationDrawer() {

        //set the first item in navigation menu as checked
        navigationView.getMenu().getItem(0).setChecked(true);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.nav_popular_item:

                        //Check the current selected navigation item
                        if(navItemIndex != 0 ){
                            navItemIndex = 0;
                            toolbar.setTitle(R.string.popular);
                            initMainRecycler();
                        }

                        break;

                    case R.id.nav_top_item:

                        //Check the current selected navigation item
                        if(navItemIndex != 1 ){
                            navItemIndex = 1;
                            toolbar.setTitle(R.string.top_rated);
                            initMainRecycler();
                        }

                        break;

                    case R.id.nav_search_item:

                        //Calling Navigation Controller to start search activity
                        NavigationController.startSearchMovieActivity(MainActivity.this);
                        break;

                    default:
                        navItemIndex = 0;
                }

                drawer.closeDrawers();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //Calling sync state is necessary or else the hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void initMainRecycler() {

        // Defining the LinearLayoutManager having a Grid structure with 2 rows
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.VERTICAL, false);

        //Checking which option is selected from the navigation menu
        if(navItemIndex == 0) {

            //Calling the popular movies API with respective parameters
            ApiCalls.getPopularMovies(MainActivity.this, currentPage, swipeRefresh, new MoviesResultsListener() {

                @Override
                public void onResultsFound(MoviesResponse moviesResponse) {

                    currentPage = moviesResponse.getPage();

                    mAdapter = new MainRecyclerAdapter(moviesResponse.getResults(), MainActivity.this);

                    mainRecycler.setLayoutManager(mLayoutManager);

                    mainRecycler.setAdapter(mAdapter);
                }

            });

        }else if(navItemIndex == 1){

            //Calling the top rated movies API with respective parameters
            ApiCalls.getTopRatedMovies(MainActivity.this, currentPage, swipeRefresh, new MoviesResultsListener() {

                @Override
                public void onResultsFound(MoviesResponse moviesResponse) {

                    currentPage = moviesResponse.getPage();

                    mAdapter = new MainRecyclerAdapter(moviesResponse.getResults(), MainActivity.this);

                    mainRecycler.setLayoutManager(mLayoutManager);

                    mainRecycler.setAdapter(mAdapter);
                }

            });
        }

        // Adding the on scroll listener to serve the endless scroll (Pagination)
        mainRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                // Check that if the previous api call is still in progress
                if (!swipeRefresh.isRefreshing()) {
                    if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount)
                    {
                        //Checking which option is selected from the navigation menu
                        if(navItemIndex == 0) {

                            // Again making the api call incrementing the page with 1
                            ApiCalls.getPopularMovies(MainActivity.this, ++currentPage, swipeRefresh, new MoviesResultsListener() {
                                @Override
                                public void onResultsFound(MoviesResponse moviesResponse) {

                                    // Adding the fetched results in the previous data set
                                    mAdapter.loadMore(moviesResponse.getResults());

                                    // Notifying the recycler about the new items added
                                    mAdapter.notifyDataSetChanged();
                                }
                            });

                        }else if(navItemIndex == 1){

                            // Again making the api call incrementing the page with 1
                            ApiCalls.getTopRatedMovies(MainActivity.this, ++currentPage, swipeRefresh, new MoviesResultsListener() {
                                @Override
                                public void onResultsFound(MoviesResponse moviesResponse) {

                                    // Adding the fetched results in the previous data set
                                    mAdapter.loadMore(moviesResponse.getResults());

                                    // Notifying the recycler about the new items added
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}
