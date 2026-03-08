package com.bjcc.posts.features.favoriteposts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bjcc.posts.R
import com.bjcc.posts.features.favoriteposts.presentation.adapter.FavoritePostsAdapter
import com.bjcc.posts.features.posts.presentation.PostsViewModel

class FavoritePostsFragment : Fragment() {

    private lateinit var favoritePostsAdapter: FavoritePostsAdapter
    private lateinit var favoritePostsRecyclerView: RecyclerView
    private lateinit var tvEmptyMessage: TextView

    private val viewModel: PostsViewModel by viewModels({
        //  First parent of the childFragment on navigation component is the NavHostFragment.
        requireParentFragment().requireParentFragment()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_posts, container, false)
            .also {
                favoritePostsRecyclerView = it.findViewById(R.id.posts_recycler_view)
                tvEmptyMessage = it.findViewById(R.id.tv_empty_message)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            tvEmptyMessage.visibility =
                if (state.favoritePosts.isEmpty()) View.VISIBLE else View.GONE
            favoritePostsAdapter.updateList(state.favoritePosts)
        }
    }

    private fun setupRecyclerView() {
        favoritePostsAdapter = FavoritePostsAdapter(
            onItemSwiped = { viewModel.onToggleFavorite(it) }
        )
        favoritePostsRecyclerView.adapter = favoritePostsAdapter

        val swipeItemHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    val position = viewHolder.bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        favoritePostsAdapter.removeItemAt(position)
                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(swipeItemHelperCallback)
        itemTouchHelper.attachToRecyclerView(favoritePostsRecyclerView)
    }
}