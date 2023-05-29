package com.alexleru.showpitcture.presentation.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.FragmentListOfPicturesBinding
import com.alexleru.showpitcture.presentation.viewModel.ListOfPicturesViewModel

class ListOfPicturesFragment : Fragment() {

    private var _binding: FragmentListOfPicturesBinding? = null
    private val binding: FragmentListOfPicturesBinding
        get() = _binding ?: throw RuntimeException("FragmentListOfPicturesBinding =  null")

    private val viewModelList by lazy {
        ViewModelProvider(this)[ListOfPicturesViewModel::class.java]
    }

    private lateinit var pictureAdapter: PictureAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView()
        viewModelList.listOfPicture.observe(viewLifecycleOwner) {
            pictureAdapter.list = it
        }
        pictureAdapter.clickOnPicture = {
            openItemFragment(it)
        }
    }

    private fun recyclerView() {
        val recycler = binding.recyclerView
        pictureAdapter = PictureAdapter()
        recycler.adapter = pictureAdapter
        val columnCount = setColumnCount()
        recycler.layoutManager = GridLayoutManager(context, columnCount)
    }

    private fun setColumnCount(): Int {
        val screenOrientation = requireContext().resources.configuration.orientation
        return if (screenOrientation == Configuration.ORIENTATION_PORTRAIT)
            2
        else
            3
    }

    private fun openItemFragment(url: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivity, ItemOfPictureFragment.newInstance(url)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}