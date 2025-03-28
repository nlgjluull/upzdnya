package com.example.upzn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView



    class MainActivity5 : AppCompatActivity() {
        private lateinit var recyclerView: RecyclerView
        private lateinit var adapter: ItemAdapter
        private var isLoading = false
        private var currentPage = 1
        private val itemsPerPage = 3

        private val originalPosts = listOf(
            Item(
                id = 1,
                logoResId = R.drawable.logogo,
                title = "Программистам и другим профессионалам!",
                date = "22 марта в 11:30",
                imageResId = R.drawable.post2,
                description = "Подписывайтесь на этот бесплатный курс! Мы собрали самые интересные факты для начала карьеры в мире информационных технологий. Скорее к нам!!!",
                likeCount = 55,
                shareCount = 5,
                commentCount = 10,
                isLiked = false
            ),
            Item(
                id = 2,
                logoResId = R.drawable.logogo,
                title = "300-летие Воронежской губернии.",
                date = "23 марта в 15:16",
                imageResId = R.drawable.post11,
                description = "В честь этого важного события мы предлагаем окунуться в историю региона...",
                likeCount = 52,
                shareCount = 6,
                commentCount = 15,
                isLiked = false
            ),
            Item(
                id = 3,
                logoResId = R.drawable.logogo,
                title = "Профилактическая встреча с сотрудником ОГИБДД",
                date = "24 марта в 15:30",
                imageResId = R.drawable.post22,
                description = "В ходе профилактической беседы инспектор по пропаганде ОГИБДД...",
                likeCount = 52,
                shareCount = 6,
                commentCount = 20,
                isLiked = false
            )
        )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main5)

            // Находим кнопку и устанавливаем обработчик клика
            val backButton: ImageButton = findViewById(R.id.imageButton7)
            backButton.setOnClickListener {
                // Возвращаемся в MainActivity2
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish() // Закрываем текущую активность
            }

            setupRecyclerView()
            loadInitialData()

            val mainView = findViewById<View>(R.id.main)
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { view: View, insets: WindowInsetsCompat ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        private fun setupRecyclerView() {
            recyclerView = findViewById(R.id.recyclerView)
            adapter = ItemAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        loadMoreData()
                        isLoading = true
                    }
                }
            })
        }

        private fun loadInitialData() {
            val initialItems = generateItems(1, itemsPerPage)
            adapter.submitList(initialItems)
        }

        private fun loadMoreData() {
            Handler(Looper.getMainLooper()).postDelayed({
                val newItems = generateItems(currentPage * itemsPerPage + 1, itemsPerPage)
                val currentList = adapter.currentList.toMutableList()
                currentList.addAll(newItems)
                adapter.submitList(currentList)
                currentPage++
                isLoading = false
            }, 1000)
        }

        private fun generateItems(startId: Int, count: Int): List<Item> {
            return List(count) { index: Int ->
                val originalPost = originalPosts[index % originalPosts.size]
                Item(
                    id = (startId + index).toLong(),
                    logoResId = originalPost.logoResId,
                    title = originalPost.title,
                    date = originalPost.date,
                    imageResId = originalPost.imageResId,
                    description = originalPost.description,
                    likeCount = originalPost.likeCount,
                    shareCount = originalPost.shareCount,
                    commentCount = originalPost.commentCount,
                    isLiked = originalPost.isLiked
                )
            }
        }
    }
