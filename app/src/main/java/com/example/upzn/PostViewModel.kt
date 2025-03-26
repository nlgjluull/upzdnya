package com.example.upzn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.upzn.data.AppDatabase
import com.example.upzn.data.PostEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val postDao = database.postDao()

    private val _posts = MutableStateFlow<List<Item>>(emptyList())
    val posts: StateFlow<List<Item>> = _posts

    private val _selectedPost = MutableStateFlow<Item?>(null)
    val selectedPost: StateFlow<Item?> = _selectedPost

    init {
        viewModelScope.launch {
            postDao.getAllPosts().map { entities ->
                entities.map { it.toItem() }
            }.collect { items ->
                _posts.value = items
            }
        }
    }

    fun setSelectedPost(post: Item?) {
        _selectedPost.value = post
    }

    fun addPost(item: Item) {
        viewModelScope.launch {
            postDao.insertPost(item.toEntity())
        }
    }

    fun updatePost(item: Item) {
        viewModelScope.launch {
            postDao.updatePost(item.toEntity())
        }
    }

    fun deletePost(item: Item) {
        viewModelScope.launch {
            postDao.deletePost(item.toEntity())
        }
    }

    fun incrementLikes(postId: Long) {
        viewModelScope.launch {
            postDao.incrementLikes(postId)
        }
    }

    fun decrementLikes(postId: Long) {
        viewModelScope.launch {
            postDao.decrementLikes(postId)
        }
    }

    fun incrementShares(postId: Long) {
        viewModelScope.launch {
            postDao.incrementShares(postId)
        }
    }

    fun incrementComments(postId: Long) {
        viewModelScope.launch {
            postDao.incrementComments(postId)
        }
    }

    fun addSamplePosts() {
        viewModelScope.launch {
            val samplePosts = listOf(
                Item(
                    id = 1,
                    title = "НМедиа ГБПОУ ВО БТПИТ",
                    description = "21 марта стартует бесплатный курс Основы HTML и CSS",
                    date = "21 марта",
                    logoResId = R.drawable.logogo,
                    imageResId = R.drawable.post2,
                    likeCount = 22,
                    commentCount = 13,
                    shareCount = 8,
                    isLiked = false
                ),
                Item(
                    id = 2,
                    title = "НМедиа ГБПОУ ВО БТПИТ",
                    description = "Это базовый курс, который пригодится не только начинающим разработчикам, но и всем, кто работает с вебом: дизайнерам, маркетологам и контент-менеджерам.",
                    date = "20 марта",
                    logoResId = R.drawable.logogo,
                    imageResId = R.drawable.post3,
                    likeCount = 15,
                    commentCount = 7,
                    shareCount = 4,
                    isLiked = false
                ),
                Item(
                    id = 3,
                    title = "НМедиа ГБПОУ ВО БТПИТ",
                    description = "На курсе вы научитесь создавать простые веб-страницы, стилизовать их и делать адаптивными для разных устройств.",
                    date = "19 марта",
                    logoResId = R.drawable.logogo,
                    imageResId = R.drawable.post11,
                    likeCount = 10,
                    commentCount = 5,
                    shareCount = 3,
                    isLiked = false
                )
            )
            samplePosts.forEach { post ->
                postDao.insertPost(post.toEntity())
            }
        }
    }

    private fun PostEntity.toItem(): Item {
        return Item(
            id = id,
            title = title,
            description = description,
            date = date,
            logoResId = logoResId,
            imageResId = imageResId,
            likeCount = likeCount,
            commentCount = commentCount,
            shareCount = shareCount,
            isLiked = isLiked
        )
    }

    private fun Item.toEntity(): PostEntity {
        return PostEntity(
            id = id,
            title = title,
            description = description,
            date = date,
            logoResId = logoResId,
            imageResId = imageResId,
            likeCount = likeCount,
            commentCount = commentCount,
            shareCount = shareCount,
            isLiked = isLiked
        )
    }
} 