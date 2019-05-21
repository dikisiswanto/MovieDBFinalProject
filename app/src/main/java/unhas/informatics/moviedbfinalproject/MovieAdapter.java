package unhas.informatics.moviedbfinalproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w185";
    ArrayList<MovieItems> mData = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View movieView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_movie_item, viewGroup, false);
        return new MovieViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.bind(mData.get(i));
        movieViewHolder.seeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movieItem", mData.get(movieViewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, releaseDate, description;
        ImageView poster;
        Button seeDetails;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            releaseDate = itemView.findViewById(R.id.release_date);
            description = itemView.findViewById(R.id.description);
            poster = itemView.findViewById(R.id.poster);
            seeDetails = itemView.findViewById(R.id.see_details);
        }

        public void bind(MovieItems movieItems) {
            title.setText(movieItems.getTitle());
            releaseDate.setText(movieItems.getReleaseDate());
            description.setText(movieItems.getDescription());
            Glide.with(context)
                    .load(IMAGE_ENDPOINT + movieItems.getPoster())
                    .apply(new RequestOptions().override(150, 200))
                    .into(poster);
        }
    }
}
