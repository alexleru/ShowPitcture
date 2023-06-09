package com.alexleru.showpitcture.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.ItemData.Picture
import com.alexleru.showpitcture.domain.entity.ItemData.TextTitle
import com.alexleru.showpitcture.getDate

class RepositoryImpl : Repository {

    private val pictureLiveItemList = MutableLiveData<List<ItemData>>(emptyList())
    private val pictures = sortedSetOf<Picture>()
    private val listTextTitle = mutableListOf<TextTitle>()

    //region init mock data for pictures
    init {
        pictures.add(
            Picture(
                url = "img_0.webp",
                date = getDate()
            )
        )
        pictures.add(
            Picture(
                url = "img_2.webp",
                date = getDate()
            )
        )
        pictures.add(
            Picture(
                url = "img_3.webp",
                date = getDate()
            )
        )
        pictures.add(
            Picture(
                url = "img_4.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_5.webp",
                date = getDate(2)
            )
        )
        pictures.add(
            Picture(
                url = "img_0.webp",
                date = getDate(3)
            )
        )
        pictures.add(
            Picture(
                url = "img_1.webp",
                date = getDate(4)
            )
        )
        pictures.add(
            Picture(
                url = "img_2.webp",
                date = getDate(5)
            )
        )
        pictures.add(
            Picture(
                url = "img_3.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_4.webp",
                date = getDate(5)
            )
        )
        pictures.add(
            Picture(
                url = "img_5.webp",
                date = getDate(14)
            )
        )
        pictures.add(
            Picture(
                url = "img_0.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_1.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_2.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_3.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_4.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_5.webp",
                date = getDate(1)
            )
        )
        pictures.add(
            Picture(
                url = "img_0.webp",
                date = getDate(1)
            )
        )
        updateList()
    }
    //endregion

    override fun updateList() {
        createTextTitleList()
        pictureLiveItemList.value = insertTextTitleInList()
    }

    private fun insertTextTitleInList(): List<ItemData> {
        val itemDataList = pictures.map { it as ItemData }.toMutableList()
        for (i in listTextTitle)
            itemDataList.add(i.position, i)
        return itemDataList
    }

    private fun createTextTitleList() {
        var nextPosition = 0
        for (i in 0..2) {
            when (i) {
                0 -> {
                    val countToday = pictures.count { it.date.isEqual(getDate()) }
                    nextPosition = addTitleText(TODAY, countToday, nextPosition)
                }

                1 -> {
                    val countYesterday = pictures.count {
                        it.date.isEqual(getDate(1))
                    }
                    nextPosition = addTitleText(YESTERDAY, countYesterday, nextPosition)
                }

                else -> {
                    val countOldest = pictures.count {
                        it.date.isBefore(getDate(1))
                    }
                    nextPosition = addTitleText(OLDEST, countOldest, nextPosition)
                }
            }
        }
    }

    private fun addTitleText(title: String, count: Int, nextPosition: Int): Int {
        val isExist = listTextTitle.firstOrNull { it.text == title }
        return if (count != 0) {
            if (isExist != null) {
                val newValue = isExist.copy(position = nextPosition)
                listTextTitle.remove(isExist)
                listTextTitle.add(newValue)
            } else {
                listTextTitle += TextTitle(text = title, position = nextPosition)
            }

            nextPosition + count + 1
        } else {
            if (isExist != null) {
                listTextTitle.remove(isExist)
            }
            nextPosition
        }
    }

    override fun getListOfItem(): LiveData<List<ItemData>> {
        return pictureLiveItemList
    }

    override fun setFavoriteOfPicture(picture: Picture) {
        val newValue = picture.copy(favorite = !picture.favorite)
        pictures.remove(picture)
        pictures.add(newValue)
        updateList()
    }

    companion object {
        private const val TODAY = "Today"
        private const val YESTERDAY = "Yesterday"
        private const val OLDEST = "Oldest"
    }
}