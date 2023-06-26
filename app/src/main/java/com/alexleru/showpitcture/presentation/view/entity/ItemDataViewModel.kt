package com.alexleru.showpitcture.presentation.view.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.joda.time.LocalDate
import java.util.UUID

sealed class ItemDataViewModel(open val uuid: UUID){
    @Parcelize
    data class PictureViewModel(
        override val uuid: UUID = UUID.randomUUID(),
        val url: String,
        val date: LocalDate,
        val favorite: Boolean = false
    ) : ItemDataViewModel(uuid), Parcelable, Comparable<PictureViewModel> {
        override fun compareTo(other: PictureViewModel): Int {
            return when (val compareDate = this.date.compareTo(other.date) * (-1)) {
                0 -> this.uuid.compareTo(other.uuid)
                else -> return compareDate
            }
        }
    }

    data class TextTitleViewModel
        (
        override val uuid: UUID = UUID.randomUUID(),
        val text: String,
        val position: Int
    ): ItemDataViewModel(uuid)
}
