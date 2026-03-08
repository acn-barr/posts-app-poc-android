package com.bjcc.posts.features.posts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bjcc.posts.R
import com.bjcc.posts.features.favoriteposts.domain.entity.FavoritePost
import com.bjcc.posts.features.posts.domain.entity.Post
import com.bjcc.posts.features.posts.presentation.adapter.PostAdapterData
import com.bjcc.posts.features.posts.presentation.adapter.PostsAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator

class PostsFragment : Fragment() {

    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var tvErrorMessage: TextView

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
            .also {
                postsRecyclerView = it.findViewById(R.id.posts_recycler_view)
                loadingIndicator = it.findViewById(R.id.loading_indicator)
                tvErrorMessage = it.findViewById(R.id.tv_error_message)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.state.observe(viewLifecycleOwner) { state ->
            loadingIndicator.visibility = if (state.isLoading) View.VISIBLE else View.GONE

            if (state.error != null) {
                tvErrorMessage.text = state.error
                postsAdapter.updateList(emptyList())
            } else {
                tvErrorMessage.text = ""
                updateAdapterList(state.posts, state.favoritePosts)
            }
        }
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter(
            onItemClick = { viewModel.onToggleFavorite(it) }
        )
        postsRecyclerView.adapter = postsAdapter
    }

    private fun updateAdapterList(
        posts: List<Post>,
        favoritePosts: List<FavoritePost>
    ) {
        val favoritePostIds =
            favoritePosts.map { it.id }.toList()

        val dataList = posts.map { post ->
            PostAdapterData(post, favoritePostIds.contains(post.id))
        }.toList()

        postsAdapter.updateList(dataList)
    }
}