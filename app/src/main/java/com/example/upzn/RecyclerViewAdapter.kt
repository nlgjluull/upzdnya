package com.example.upzn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

data class RecyclerViewItem(
    val title: String,
    val description: String,
    val fullDescription: String = "",
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0,
    val isLiked: Boolean = false
)

class RecyclerViewAdapter : ListAdapter<RecyclerViewItem, RecyclerViewAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val fullDescriptionTextView: TextView = itemView.findViewById(R.id.textView4)
        private val showMoreTextView: TextView = itemView.findViewById(R.id.showMoreTextView)
        private val postImageView: ImageView = itemView.findViewById(R.id.postImageView)
        private val likeButton: ImageButton = itemView.findViewById(R.id.likeButton)
        private val likedButton: ImageButton = itemView.findViewById(R.id.likedButton)
        private val commentButton: ImageButton = itemView.findViewById(R.id.commentButton)
        private val shareButton: ImageButton = itemView.findViewById(R.id.shareButton)
        private val menuButton: ImageButton = itemView.findViewById(R.id.menuButton)
        private val likeCountTextView: TextView = itemView.findViewById(R.id.likeCountTextView)
        private val commentCountTextView: TextView = itemView.findViewById(R.id.commentCountTextView)
        private val shareCountTextView: TextView = itemView.findViewById(R.id.shareCountTextView)

        fun bind(item: RecyclerViewItem) {
            titleTextView.text = item.title
            descriptionTextView.text = item.description
            fullDescriptionTextView.text = item.fullDescription
            
            // Set counts
            likeCountTextView.text = item.likes.toString()
            commentCountTextView.text = item.comments.toString()
            shareCountTextView.text = item.shares.toString()
            
            // Set like state
            if (item.isLiked) {
                likeButton.visibility = View.GONE
                likedButton.visibility = View.VISIBLE
            } else {
                likeButton.visibility = View.VISIBLE
                likedButton.visibility = View.GONE
            }

            // Set click listeners
            likeButton.setOnClickListener {
                // Handle like
            }

            likedButton.setOnClickListener {
                // Handle unlike
            }

            commentButton.setOnClickListener {
                // Handle comment
            }

            shareButton.setOnClickListener {
                // Handle share
            }

            menuButton.setOnClickListener {
                // Handle menu
            }

            showMoreTextView.setOnClickListener {
                // Handle show more
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<RecyclerViewItem>() {
        override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
            return oldItem == newItem
        }
    }
} 