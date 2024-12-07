package com.example.moviesapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieItemBinding
import androidx.recyclerview.widget.DiffUtil

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movieList: List<Movie> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            binding.apply {
                movieNameTv.text = movie.name
                movieDescriptionTv.text = movie.description
                movieRateTv.text = movie.rating.toString()
                moviePublishDateTv.text = movie.publishDate
                movieImageIv.setImageResource(movie.imageResId)
            }
        }
    }

    fun updateMovieList(newMovieList: List<Movie>) {
        val diffCallback = DiffUtil(oldList = this.movieList, newList = newMovieList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.movieList = newMovieList
        diffResult.dispatchUpdatesTo(this)
    }
}