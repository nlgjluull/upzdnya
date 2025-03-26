class Item {
    data class Item(
        val id: Int,
        val logoResId: Int,
        val title: String,
        val date: String,
        val imageResId: Int,
        val description: String,
        var likeCount: Int = 0,
        var shareCount: Int = 0,
        var commentCount: Int = 0,
        var isLiked: Boolean = false)
}