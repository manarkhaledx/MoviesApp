package com.example.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val moviesAdapter by lazy { MoviesAdapter() }
    private lateinit var binding: ActivityMainBinding
    private var isShowingUpdatedList = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.movieRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.movieRecyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_slide)



        binding.movieRecyclerView.adapter = moviesAdapter
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)

        moviesAdapter.updateMovieList(moviesList())


        intent.getStringExtra("username")?.let {
            binding.welcomeTv.text = "Welcome $it"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.more -> {
                val updatedList = if (isShowingUpdatedList) moviesList() else updatedMoviesList()
                moviesAdapter.updateMovieList(updatedList)
                isShowingUpdatedList = !isShowingUpdatedList
                binding.movieRecyclerView.scheduleLayoutAnimation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moviesList() = listOf(
        Movie(1, "Interstellar", "A team of explorers travel through a wormhole in space to ensure humanity's survival", 8.6, "2014", R.drawable.interstellar),
        Movie(2, "The Prestige", "Two rival magicians engage in a battle to create the ultimate illusion", 8.5, "2006", R.drawable.prestige),
        Movie(3, "Whiplash", "A young drummer enrolls at a music conservatory and faces a ruthless instructor", 8.5, "2014", R.drawable.whiplash),
        Movie(4, "Parasite", "A poor family schemes to become employed by a wealthy family by infiltrating their household", 8.5, "2019", R.drawable.parasite),
        Movie(5, "Gladiator", "A betrayed Roman general seeks revenge against the corrupt emperor who murdered his family", 8.5, "2000", R.drawable.gladiator)
    )

    private fun updatedMoviesList() = listOf(
        Movie(1, "Coco", "A young boy journeys to the Land of the Dead to discover his family's history and passion for music", 8.4, "2017", R.drawable.coco),
        Movie(2, "Spirited Away", "A young girl becomes trapped in a mysterious, magical world ruled by spirits and creatures", 8.6, "2001", R.drawable.spirited),
        Movie(3, "Mad Max: Fury Road", "In a post-apocalyptic wasteland, a drifter and a warrior join forces to overthrow a tyrant", 8.1, "2015", R.drawable.mad),
        Movie(4, "A Beautiful Mind", "The story of a brilliant but troubled mathematician and his struggle with mental illness", 8.2, "2001", R.drawable.beautiful),
        Movie(5, "The Grand Budapest Hotel", "A whimsical tale of a hotel concierge and his protégé involved in a murder mystery", 8.1, "2014", R.drawable.hotel)
    )
}

