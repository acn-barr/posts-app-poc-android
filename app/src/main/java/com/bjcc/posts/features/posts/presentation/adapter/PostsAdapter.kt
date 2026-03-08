package com.bjcc.posts.features.posts.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bjcc.posts.R
import com.bjcc.posts.features.posts.domain.entity.Post

data class PostAdapterData(
    val post: Post,
    val isFavorite: Boolean
)

class PostsAdapter(
    private var posts: MutableList<PostAdapterData> = emptyList<PostAdapterData>().toMutableList(),
    private var onItemClick: ((postId: Int) -> Unit)
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

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
        val post = posts[position].post

        viewHolder.tvTitle.text = post.title
        viewHolder.tvBody.text = post.body

        val drawable =
            if (posts[position].isFavorite) R.drawable.favorite_marker
            else R.drawable.unfavorite_marker
        viewHolder.imageFavorite.setImageDrawable(
            ContextCompat.getDrawable(viewHolder.itemView.context, drawable)
        )

        viewHolder.itemView.setOnClickListener {
            onItemClick.invoke(post.id)
        }
    }

    override fun getItemCount() = posts.size

    fun updateList(list: List<PostAdapterData>) {
        posts.clear()
        posts.addAll(list)
        notifyDataSetChanged()
    }
}