package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import java.util.List;

//it is important to import the MovieAdapter ViewHolder
//Adapter is an abstract class, so there are methods that should be overwritten
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    //where is the adapter being constructed from? define a context.
    Context context;

    //the actual data the adapter needs to hold on to.
    List<Movie> movies;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    /**
     * Inflates the layout and return to the holder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateVieHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        //wrap the view inside the view holder
        return new ViewHolder(movieView);
    }

    /**
     * populates data to the view  through the viewholder.
     * Takes data from the position and inserts it into the view contained in the vh.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindVieHolder "+position);

        //Get the movie at the position
        Movie movie = movies.get(position);

        //Bind the movie data into the viewholder
        holder.bind(movie);

    }

    /**
     *
     * @return the total count of items in the list.
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //define an inner viewholder class
    public class ViewHolder extends RecyclerView.ViewHolder{

        //define member variables from the item_movie.xml file to
        //bind data

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        //viewHolder is a representation of the view (the image-text-text for each movie).
        //so, pass that in. i.e. from item_movie.xml.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //define where the Text views and image views are coming from
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

        /**
         * populate views with getter methods
         * @param movie
         */
        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;

            //if phone is in landscape, url is backdrop
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
            {
                imageUrl = movie.getBackdropPath();

            }
            else
            {
                imageUrl= movie.getPosterPath();
            }

            //render an image using the Glide library
            Glide.with(context).load(imageUrl).into(ivPoster);

            //responds when the title is clicked.
            /*
            We want the click listener to respond whenever the
            viewholder is clicked. Steps outlined below:
             */
            //Step 1. Register Click listener on the whole row
            //             will need to get a reference to the container
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // 2. Navigate to a new activity on tap.
                    Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}
