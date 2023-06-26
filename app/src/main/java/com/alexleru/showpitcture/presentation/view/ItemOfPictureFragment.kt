package com.alexleru.showpitcture.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alexleru.showpitcture.databinding.FragmentItemOfPictureBinding
import com.alexleru.showpitcture.formatDate
import com.alexleru.showpitcture.fromAssertToDrawable
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.PictureViewModel

class ItemOfPictureFragment : Fragment() {

    private var _binding: FragmentItemOfPictureBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentItemOfPictureBinding == null")

    private lateinit var picture: PictureViewModel

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
        val drawable = requireContext().fromAssertToDrawable(picture.url)
        binding.imageViewMain.setImageCompound(drawable)
        binding.imageViewMain.textDate = picture.date.formatDate()
    }

    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_PICTURE, PictureViewModel::class.java)?.let {
                picture = it
            }
        } else {
            @Suppress("DEPRECATION")
            requireArguments().getParcelable<PictureViewModel>(ARG_PICTURE)?.let {
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

        fun newInstance(picture: PictureViewModel) = ItemOfPictureFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PICTURE, picture)
            }
        }
    }
}