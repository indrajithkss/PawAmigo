package com.app.pawamigo.community

import com.google.firebase.Timestamp

data class Post(
    var postId: String = "",
    var userId: String = "",
    var userName: String = "",
    var userAvatar: String? = null,
    var text: String? = null,
    var imageUrl: String? = null,
    var timestamp: Long = 0L,
    var likeCount: Long = 0L
)