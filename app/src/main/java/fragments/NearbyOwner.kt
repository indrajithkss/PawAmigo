package com.app.pawamigo.fragments

data class NearbyOwner(
    val ownerName: String,
    val petName: String,
    val petType: String,
    val distance: String,
    val imageRes: Int
)
