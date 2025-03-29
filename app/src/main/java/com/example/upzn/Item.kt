package com.example.upzn

data class Item(
    val id: Long,
    val title: String,
    val description: String,
    val date: String,
    val logoResId: Int,
    val imageResId: Int,
    var likeCount: Int = 0,
    var commentCount: Int = 0,
    var shareCount: Int = 0,
    var isLiked: Boolean = false
)