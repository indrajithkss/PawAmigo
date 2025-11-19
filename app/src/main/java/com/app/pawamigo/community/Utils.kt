package com.app.pawamigo.community

fun timeAgo(ts: Long): String {
    val diff = System.currentTimeMillis() - ts
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    return when {
        seconds < 60 -> "${seconds}s"
        minutes < 60 -> "${minutes}m"
        hours < 24 -> "${hours}h"
        days < 7 -> "${days}d"
        else -> java.text.SimpleDateFormat("dd MMM yyyy").format(java.util.Date(ts))
    }
}
