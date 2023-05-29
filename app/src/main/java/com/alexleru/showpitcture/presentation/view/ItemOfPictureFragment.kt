package com.alexleru.showpitcture.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.FragmentItemOfPictureBinding


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
        setupImage()
        setupOnClick()
    }

    private fun setupImage() {
        //повтор как и в адаптере вынести в файл util
        val png = requireContext().assets.open(url)
        val dw = Drawable.createFromStream(png, null)
        //повтор
        binding.imageView.setImageDrawable(dw)
    }

    private fun setupOnClick(){
        binding.imageView.setOnClickListener { goBack() }
    }

    private fun parseArgs(){
        requireArguments().getString(URL)?.let {
            url = it
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
        private const val URL = "URL"

        fun newInstance(url: String): ItemOfPictureFragment {
            val args = Bundle()
            args.putString(URL, url)
            val fragment = ItemOfPictureFragment()
            fragment.arguments = args
            return fragment
        }
    }
}