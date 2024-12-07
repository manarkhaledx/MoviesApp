package com.example.moviesapp

data class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val rating: Double,
    val publishDate: String,
    val imageResId: Int
)
