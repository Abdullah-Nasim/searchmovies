package myown.searchmovies.activities.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myown.searchmovies.Constants;
import myown.searchmovies.R;
import myown.searchmovies.activities.viewholders.MainRecyclerViewHolder;
import myown.searchmovies.network.models.MoviesResponse;
import myown.searchmovies.utils.NavigationController;

/**
 * Created by Netaq on 9/14/2017.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {

    ArrayList<MoviesResponse.Result> mDataSet;
    Context mContext;

    public MainRecyclerAdapter(ArrayList<MoviesResponse.Result> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MainRecyclerViewHolder viewHolder = new MainRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item, parent, false));

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, final int position) {

        holder.movieName.setText(mDataSet.get(position).getTitle());
        holder.moviePoster.setImageURI(Uri.parse(Constants.Image_Base_URL + mDataSet.get(position).getPoster_path()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call the navigation controller to start movie details activity
                NavigationController.startMovieDetailsActivity(mContext, mDataSet.get(position).getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    //The following function is responsible of adding more movies in the previous data set
    public void loadMore (ArrayList<MoviesResponse.Result> moreMovies){

        for (MoviesResponse.Result mr : moreMovies){

            mDataSet.add(mr);

        }
    }
}
