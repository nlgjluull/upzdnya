package com.example.upzn.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
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