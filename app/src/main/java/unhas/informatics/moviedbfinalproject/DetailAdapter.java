package unhas.informatics.moviedbfinalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    private static final String IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w342";
    MovieItems mData;
    private Context context;

    public DetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(MovieItems data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View detailView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_movie_item, viewGroup, false);
        return new DetailViewHolder(detailView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int i) {
        detailViewHolder.bind(mData);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class DetailViewHolder extends RecyclerView.ViewHolder {
        ImageView backdrop, poster;
        TextView title, rating, description, release_date;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            backdrop = itemView.findViewById(R.id.backdrop);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            description = itemView.findViewById(R.id.description);
            release_date = itemView.findViewById(R.id.release_date);
        }

        public void bind(MovieItems mData) {
            Glide.with(context)
                    .load(IMAGE_ENDPOINT + mData.getPoster())
                    .apply(new RequestOptions())
                    .into(poster);
            Glide.with(context)
                    .load(IMAGE_ENDPOINT + mData.getBackdrop())
                    .apply(new RequestOptions())
                    .into(backdrop);
            title.setText(mData.getTitle());
            rating.setText(mData.getRating() + " / 10");
            release_date.setText(mData.getReleaseDate());
            description.setText(mData.getDescription());
        }
    }
}
