package com.bjcc.posts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val view = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            view.fitsSystemWindows = true
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            WindowInsetsCompat
                .Builder()
                .setInsets(
                    WindowInsetsCompat.Type.systemBars(),
                    Insets.of(systemBars.left, systemBars.top, systemBars.right, 0)
                )
                .build()
                .apply {
                    ViewCompat.onApplyWindowInsets(v, this)
                }
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.app_nav_graph)
        val loadingIndicator: CircularProgressIndicator = findViewById(R.id.loading_indicator)

        viewModel.isUserLoggedIn.observe(this) { isUserLoggedIn ->
            if (isUserLoggedIn == null) {
                loadingIndicator.visibility = View.VISIBLE
            } else {
                val destinationId =
                    if (isUserLoggedIn) R.id.postsMainFragment else R.id.loginFragment

                loadingIndicator.visibility = View.GONE
                navController.setGraph(navGraph, null)
                navController.navigate(destinationId)
            }
        }
    }
}