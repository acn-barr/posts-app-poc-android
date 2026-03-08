package com.bjcc.posts.features.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bjcc.posts.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var btnLogin: Button
    private lateinit var editEmail: TextInputEditText
    private lateinit var editPassword: TextInputEditText

    private val viewModel: LoginViewModel by viewModels()

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
        return inflater.inflate(R.layout.fragment_login, container, false)
            .also {
                btnLogin = it.findViewById(R.id.btn_login)
                editEmail = it.findViewById(R.id.edit_email)
                editPassword = it.findViewById(R.id.edit_password)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmail.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailUpdate(text.toString())
        }

        editPassword.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordUpdate(text.toString())
        }

        btnLogin.setOnClickListener { viewModel.login() }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            btnLogin.isEnabled = state.isLoginButtonEnabled
        }
    }
}