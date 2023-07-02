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
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView
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
        observeViewModel()
        swipeView()
    }

    private fun observeViewModel() {
        viewModelList.listOfCatPicture.observe(viewLifecycleOwner) {
            pictureAdapter.submitList(it)
        }

        viewModelList.progressPosition.observe(viewLifecycleOwner) {
            binding.customProgressBar.animationProgress(it)
        }
    }

    private fun recyclerView() {
        pictureAdapter = PictureAdapter({ clickOnItem(it) }, { clickLongOnItem(it) })
        binding.recyclerView.apply {
            adapter = pictureAdapter
            val columnCount = calculateColumnCount()
            PictureAdapter.PICTURE_VIEW_TYPE
            val gridLayoutManager = GridLayoutManager(context, columnCount)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (pictureAdapter.currentList[position]) {
                        is CatPictureView -> SPAIN_SIZE_ONE
                        else -> throw RuntimeException("Not found ")
                    }
                }
            }
            layoutManager = gridLayoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastPosition =
                        (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    val firstPosition =
                        (layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                    viewModelList.position(
                        firstPosition,
                        lastPosition,
                        (adapter as PictureAdapter).itemCount
                    )
                }
            })
        }
    }

    private fun calculateColumnCount(): Int {
        val screenOrientation = requireContext().resources.configuration.orientation
        return if (screenOrientation == Configuration.ORIENTATION_PORTRAIT)
            COLUMN_SIZE_PORTRAIT
        else
            COLUMN_SIZE_LANDSCAPE
    }

    private fun clickOnItem(picture: CatPictureView) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivity, ItemOfPictureFragment.newInstance(picture)
            )
            .addToBackStack(null)
            .commit()
    }

    fun swipeView(){
        binding.swipeRefresh.setOnRefreshListener {
            //TODO update method

            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun clickLongOnItem(picture: CatPictureView) {
        //viewModelList.setFavoriteOfPicture(picture)
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