package com.bjcc.posts.features.posts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bjcc.posts.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsMainFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var navHostFragment: NavHostFragment

    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_main, container, false)
            .also {
                navHostFragment =
                    childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                navController = navHostFragment.navController
                bottomNav = it.findViewById(R.id.bottom_nav)
                toolbar = it.findViewById(R.id.toolbar)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.posts_action_bar_menu)
        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.action_logout -> {
                        viewModel.logout()
                        return true
                    }
                    else -> return false
                }
            }
        })

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.posts_fragment,
                R.id.favorite_posts_fragment
            )
        )
        bottomNav.setupWithNavController(navController)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            // Disable bottom nav items while loading
            for (i in 0..<bottomNav.menu.size) {
                bottomNav.menu[i].isEnabled = !state.isLoading
            }
        }
    }
}