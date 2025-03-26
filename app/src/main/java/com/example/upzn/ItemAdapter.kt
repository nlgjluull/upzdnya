package com.example.upzn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val onItemClick: (Item) -> Unit = {}
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val logoButton: ImageButton = itemView.findViewById(R.id.logoButton)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val showMoreTextView: TextView = itemView.findViewById(R.id.showMoreTextView)
        private val likeButton: ImageButton = itemView.findViewById(R.id.likeButton)
        private val likedButton: ImageButton = itemView.findViewById(R.id.likedButton)
        private val likeCountTextView: TextView = itemView.findViewById(R.id.likeCountTextView)
        private val commentButton: ImageButton = itemView.findViewById(R.id.commentButton)
        private val commentCountTextView: TextView = itemView.findViewById(R.id.commentCountTextView)
        private val shareButton: ImageButton = itemView.findViewById(R.id.shareButton)
        private val shareCountTextView: TextView = itemView.findViewById(R.id.shareCountTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(item: Item) {
            logoButton.setImageResource(item.logoResId)
            titleTextView.text = "${item.title}\n   ${item.date}"

            descriptionTextView.text = item.description
            likeCountTextView.text = formatNumber(item.likeCount)
            shareCountTextView.text = formatNumber(item.shareCount)
            commentCountTextView.text = formatNumber(item.commentCount)

            // Show/hide liked button based on state
            likeButton.visibility = if (item.isLiked) View.GONE else View.VISIBLE
            likedButton.visibility = if (item.isLiked) View.VISIBLE else View.GONE

            // Set up click listeners
            likeButton.setOnClickListener {
                item.isLiked = true
                item.likeCount++
                likeButton.visibility = View.GONE
                likedButton.visibility = View.VISIBLE
                likeCountTextView.text = formatNumber(item.likeCount)
            }

            likedButton.setOnClickListener {
                item.isLiked = false
                item.likeCount--
                likedButton.visibility = View.GONE
                likeButton.visibility = View.VISIBLE
                likeCountTextView.text = formatNumber(item.likeCount)
            }

            shareButton.setOnClickListener {
                item.shareCount++
                shareCountTextView.text = formatNumber(item.shareCount)
            }

            // Show more/less text functionality
            var isTextExpanded = false
            showMoreTextView.setOnClickListener {
                if (!isTextExpanded) {
                    descriptionTextView.maxLines = Integer.MAX_VALUE
                    showMoreTextView.text = "Скрыть полностью"
                    isTextExpanded = true
                } else {
                    descriptionTextView.maxLines = 3
                    showMoreTextView.text = "Показать полностью"
                    isTextExpanded = false
                }
            }
        }

        private fun formatNumber(number: Int): String {
            return when {
                number >= 1000000 -> {
                    val millions = number / 1000000
                    val thousands = (number % 1000000) / 100000
                    if (thousands > 0) {
                        "$millions.${thousands}M"
                    } else {
                        "${millions}M"
                    }
                }
                number >= 10000 -> {
                    val thousands = number / 1000
                    val hundreds = (number % 1000) / 100
                    if (hundreds > 0) {
                        "$thousands.${hundreds}K"
                    } else {
                        "${thousands}K"
                    }
                }
                number >= 1000 -> {
                    val thousands = number / 1000
                    val hundreds = (number % 1000) / 100
                    if (hundreds > 0) {
                        "$thousands.${hundreds}K"
                    } else {
                        "${thousands}K"
                    }
                }
                else -> number.toString()
            }
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}