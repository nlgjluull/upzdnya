package com.example.upzn.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE id = :postId")
    suspend fun getPostById(postId: Long): PostEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)

    @Update
    suspend fun updatePost(post: PostEntity)

    @Delete
    suspend fun deletePost(post: PostEntity)

    @Query("UPDATE posts SET likeCount = likeCount + 1, isLiked = 1 WHERE id = :postId")
    suspend fun incrementLikes(postId: Long)

    @Query("UPDATE posts SET likeCount = likeCount - 1, isLiked = 0 WHERE id = :postId")
    suspend fun decrementLikes(postId: Long)

    @Query("UPDATE posts SET shareCount = shareCount + 1 WHERE id = :postId")
    suspend fun incrementShares(postId: Long)

    @Query("UPDATE posts SET commentCount = commentCount + 1 WHERE id = :postId")
    suspend fun incrementComments(postId: Long)
} 