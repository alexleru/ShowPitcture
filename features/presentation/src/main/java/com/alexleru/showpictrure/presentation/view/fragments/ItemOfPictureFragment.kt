package com.alexleru.showpictrure.presentation.view.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alexleru.showpictrure.presentation.databinding.FragmentItemOfPictureBinding
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView
import com.bumptech.glide.Glide

class ItemOfPictureFragment : Fragment() {

    private var _binding: FragmentItemOfPictureBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentItemOfPictureBinding == null")

    private lateinit var picture: CatPictureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemOfPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupImage()
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbarView
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = picture.url
        toolbar.setNavigationOnClickListener { goBack() }
    }

    private fun setupImage() {
        loadImage(picture)
        binding.imageViewMain.textDate = picture.url
    }

    private fun loadImage(picture: CatPictureView) {
        Glide.with(binding.root.context)
            .load(picture.url)
            .into(binding.imageViewMain.getImageCompound())
    }

    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_PICTURE, CatPictureView::class.java)?.let {
                picture = it
            }
        } else {
            @Suppress("DEPRECATION")
            requireArguments().getParcelable<CatPictureView>(ARG_PICTURE)?.let {
                picture = it
            }
        }
    }

    private fun goBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ARG_PICTURE = "PICTURE"

        fun newInstance(picture: CatPictureView) = ItemOfPictureFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PICTURE, picture)
            }
        }
    }
}