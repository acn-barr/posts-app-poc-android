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
    private lateinit var loginBtn: Button
    private lateinit var editEmail: TextInputEditText
    private lateinit var editPassword: TextInputEditText

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        loginBtn = view.findViewById(R.id.btn_login)
        editEmail = view.findViewById(R.id.edit_email)
        editPassword = view.findViewById(R.id.edit_password)

        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        editEmail.doOnTextChanged { text, _, _, _ ->
            viewModel.onFieldsUpdate(email = text.toString())
        }

        editPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onFieldsUpdate(password = text.toString())
        }

        loginBtn.setOnClickListener {
            viewModel.login()
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            Log.d("bars", "state: $state")
            loginBtn.isEnabled = state.isLoginButtonEnabled

            if (state.shouldNavigateToPosts) {
                findNavController().navigate(R.id.toPostsFragment)
            }
        }
    }
}