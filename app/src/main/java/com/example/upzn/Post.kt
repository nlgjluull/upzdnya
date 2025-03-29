package com.example.upzn

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int,
    val shares: Int,
    val views: Int,
    val likedByMe: Boolean = false,
    val video: String? = null // Добавляем поле для YouTube-ссылки
) 