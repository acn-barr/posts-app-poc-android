package com.bjcc.posts.features.favoriteposts.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bjcc.posts.R
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost


class FavoritePostsAdapter(
    private var favoritePosts: MutableList<FavoritePost> = emptyList<FavoritePost>().toMutableList(),
    private var onItemSwiped: ((postId: Int) -> Unit)
) : RecyclerView.Adapter<FavoritePostsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvBody: TextView = view.findViewById(R.id.tv_body)
        val imageFavorite: ImageView = view.findViewById(R.id.image_favorite)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.posts_recycler_view_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val favoritePost = favoritePosts[position]

        viewHolder.tvTitle.text = favoritePost.title
        viewHolder.tvBody.text = favoritePost.body
        viewHolder.imageFavorite.setImageDrawable(
            ContextCompat.getDrawable(viewHolder.itemView.context,  R.drawable.favorite_marker)
        )
    }

    override fun getItemCount() = favoritePosts.size

    fun updateList(list: List<FavoritePost>) {
        favoritePosts.clear()
        favoritePosts.addAll(list)
    }

    fun removeItemAt(position: Int) {
        onItemSwiped.invoke(favoritePosts[position].id)
        favoritePosts.removeAt(position)
        notifyItemRemoved(position)
    }
}