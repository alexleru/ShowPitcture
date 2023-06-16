package com.alexleru.showpitcture.presentation.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.FragmentListOfPicturesBinding
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.domain.entity.TextTitle
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
        // TODO: Сделать метод observeViewModel и в нем описывать подписки
        viewModelList.listOfPicture.observe(viewLifecycleOwner) {
            pictureAdapter.submitList(it)
        }
    }

    private fun recyclerView() {
        pictureAdapter = PictureAdapter({ clickOnItem(it) }, { clickLongOnItem(it) })
        binding.recyclerView.apply {
            adapter = pictureAdapter
            val columnCount = calculateColumnCount()
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (pictureAdapter.currentList[position]) {
                        is Picture -> SPAIN_SIZE_ONE
                        is TextTitle -> columnCount
                    }
                }

            }
            layoutManager = gridLayoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastPosition =
                        (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    binding.customProgressBar.position(
                        lastPosition,
                        (adapter as PictureAdapter).itemCount
                    )
                }
            })
        }

    }

    private fun calculateProgress(newPosition: Int) {
        // TODO: Use for calculation progress
    }

    private fun calculateColumnCount(): Int {
        val screenOrientation = requireContext().resources.configuration.orientation
        return if (screenOrientation == Configuration.ORIENTATION_PORTRAIT)
            COLUMN_SIZE_PORTRAIT
        else
            COLUMN_SIZE_LANDSCAPE
    }

    private fun clickOnItem(picture: Picture) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivity, ItemOfPictureFragment.newInstance(picture)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun clickLongOnItem(picture: Picture) {
        viewModelList.setFavoriteOfPicture(picture)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPAIN_SIZE_ONE = 1
        private const val COLUMN_SIZE_PORTRAIT = 2
        private const val COLUMN_SIZE_LANDSCAPE = 3
    }

}