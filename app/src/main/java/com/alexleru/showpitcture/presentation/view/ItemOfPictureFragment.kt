package com.alexleru.showpitcture.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.alexleru.showpitcture.databinding.FragmentItemOfPictureBinding
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.fromAssertToDrawable


class ItemOfPictureFragment : Fragment() {

    private var _binding: FragmentItemOfPictureBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentItemOfPictureBinding == null")

    private lateinit var url: String

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
        toolbar.title = url
        toolbar.setNavigationOnClickListener { goBack() }
    }

    private fun setupImage() {
        val dw = requireContext().fromAssertToDrawable(url)
        binding.imageView.setImageDrawable(dw)
    }

    private fun parseArgs(){
        requireArguments().getParcelable<Picture>(ARG_PICTURE)?.let {
            url = it.url
        }
    }

    private fun goBack(){
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        private const val ARG_PICTURE = "PICTURE"

        fun newInstance(picture: Picture) = ItemOfPictureFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PICTURE, picture)
                }
        }
    }
}