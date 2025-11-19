package com.app.pawamigo.ui.playdate

data class Playdate(
    val petName: String = "",
    val ownerName: String = "",
    val date: String = "",
    val time: String = "",
    val location: String = "",
    val notes: String = "",
    val status: String = "Pending"
)
