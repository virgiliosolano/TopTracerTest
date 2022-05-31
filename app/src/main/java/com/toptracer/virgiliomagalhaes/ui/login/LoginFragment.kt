package com.toptracer.virgiliomagalhaes.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.toptracer.virgiliomagalhaes.R
import com.toptracer.virgiliomagalhaes.databinding.FragmentLoginBinding
import com.toptracer.virgiliomagalhaes.shared.showDialog

private const val USERNAME = "username"
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogin.setOnClickListener {
            validateUsernameAndPassword()
        }
    }

    private fun validateUsernameAndPassword() {

        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        when {
            username.replace("\\s".toRegex(), "").isEmpty() -> {
                showDialog(
                    getString(R.string.dialog_title),
                    getString(R.string.username_empty_message),
                    getString(R.string.ok)
                )
            }

            password.equals(getString(R.string.password), true) -> {
                val bundle = bundleOf(USERNAME to username)
                clearUsernameAndPasswordEditText()
                findNavController().navigate(R.id.login_to_welcome_screen, bundle)
            }

            else -> {
                showDialog(
                    getString(R.string.dialog_title),
                    getString(R.string.password_invalid),
                    getString(R.string.ok)
                )
            }
        }
    }

    private fun clearUsernameAndPasswordEditText() {
        binding.etUsername.text.clear()
        binding.etPassword.text.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}