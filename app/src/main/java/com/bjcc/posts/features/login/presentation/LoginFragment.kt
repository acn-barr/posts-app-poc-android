package com.bjcc.posts.features.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bjcc.posts.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var btnLogin: Button
    private lateinit var editEmail: TextInputEditText
    private lateinit var editPassword: TextInputEditText
    private val viewModel: LoginViewModel by viewModels()

    companion object {
        private const val TAG = "LoginFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btnLogin = view.findViewById(R.id.btn_login)
        editEmail = view.findViewById(R.id.edit_email)
        editPassword = view.findViewById(R.id.edit_password)

        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            Log.d(TAG, "state: $state")
            btnLogin.isEnabled = state.isLoginButtonEnabled

            if (state.shouldNavigateToPosts) {
                findNavController().navigate(R.id.toPostsMainFragment)
            }
        }

        editEmail.doOnTextChanged { text, _, _, _ ->
            viewModel.onFieldsUpdate(email = text.toString())
        }

        editPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onFieldsUpdate(password = text.toString())
        }

        btnLogin.setOnClickListener { viewModel.login() }
    }
}