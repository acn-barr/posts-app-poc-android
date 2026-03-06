package com.bjcc.posts.features.posts.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bjcc.posts.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class PostsFragment : Fragment() {

    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var tvErrorMessage: TextView
    private val viewModel: PostsViewModel by viewModels({
        //  First parent of the childFragment on navigation component is the NavHostFragment.
        requireParentFragment().requireParentFragment()
    })

    companion object {
        private const val TAG = "PostsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false).also {
            loadingIndicator = it.findViewById(R.id.loading_indicator)
            tvErrorMessage = it.findViewById(R.id.tv_error_message)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            Log.d(TAG, "state: $state")
            loadingIndicator.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            tvErrorMessage.text = state.error
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}