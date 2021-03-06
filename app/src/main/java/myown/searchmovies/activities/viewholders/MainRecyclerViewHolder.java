package myown.searchmovies.activities.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import myown.searchmovies.R;

/**
 * Created by Netaq on 9/14/2017.
 */

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_image)
    public SimpleDraweeView moviePoster;

    @BindView(R.id.movie_name)
    public TextView movieName;

    public MainRecyclerViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);

    }
}
