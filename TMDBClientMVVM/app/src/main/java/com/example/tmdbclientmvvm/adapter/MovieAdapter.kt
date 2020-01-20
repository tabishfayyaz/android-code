package com.example.tmdbclientmvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbclientrx.R
import com.example.tmdbclientmvvm.model.Movie
import com.example.tmdbclientmvvm.view.MovieActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieAdapter (val context: Context, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies, position)
    }

    inner class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movies: List<Movie>, position: Int){

            itemView.tvTitle.text = movies[position].originalTitle
            itemView.tvRating.text = movies[position].voteAverage.toString()

            val imagePath = "https://image.tmdb.org/t/p/w500" + movies[position].posterPath
            Picasso.get().load(imagePath).placeholder(R.drawable.loading).into(itemView.ivMovie)

            itemView.setOnClickListener {

                val selectedPosition = adapterPosition
                if (selectedPosition != RecyclerView.NO_POSITION){
                    val selectedMovie = movies[selectedPosition]
                    val intent = Intent(context, MovieActivity::class.java)
                    intent.putExtra("movie", selectedMovie)
                    context.startActivity(intent)
                }
            }
        }
    }
}