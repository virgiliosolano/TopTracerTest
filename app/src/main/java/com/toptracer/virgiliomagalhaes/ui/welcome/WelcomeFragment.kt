package com.toptracer.virgiliomagalhaes.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.toptracer.virgiliomagalhaes.R
import com.toptracer.virgiliomagalhaes.databinding.FragmentWelcomeBinding
import com.toptracer.virgiliomagalhaes.shared.loadImage

private const val USERNAME = "username"
class WelcomeFragment : Fragment() {

    private lateinit var welcomeViewModel: WelcomeViewModel
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loading.visibility = View.VISIBLE

        val username = arguments?.getString(USERNAME)

        username.let {
            String.format(getString(R.string.welcome_username), it).also { welcome ->
                binding.tvWelcomeUsername.text = welcome
            }
        }

        welcomeViewModel = ViewModelProvider(this,
            WelcomeViewModelFactory())[WelcomeViewModel::class.java]

        welcomeViewModel.welcomeResult.observe(viewLifecycleOwner, Observer { resultView ->
            if (resultView == null) {
                return@Observer
            }

            resultView.error?.let { errorMessage ->
                showErrorState(errorMessage)
            }

            resultView.giphyTitle?.let { title ->
                binding.tvGiphyTitle.text = title
            }

            resultView.giphyUser?.let { authorName ->
                binding.tvGiphyAuthor.text = String.format(
                    getString(R.string.gihpy_author),
                    authorName
                )
            }

            resultView.giphyUrl?.let { giphyUrl ->

                loadImage(giphyUrl, binding.ivGif, object : RequestListener<GifDrawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        showErrorState(getString(R.string.giphy_error))
                        return false
                    }

                    override fun onResourceReady(
                        resource: GifDrawable?,
                        model: Any?,
                        target: Target<GifDrawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.loading.visibility = View.GONE
                        return false
                    }
                })
            }
        })

        binding.tvLogout.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun showErrorState(errorMessage: String) {
        binding.loading.visibility = View.GONE
        binding.tvGiphyTitle.text = getString(R.string.app_name)
        binding.tvGiphyAuthor.text = getString(R.string.app_name)
        binding.ivGif.setImageDrawable(requireActivity().getDrawable(R.mipmap.ic_launcher))
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}