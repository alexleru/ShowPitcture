package com.alexleru.showpitcture.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.joda.time.LocalDate
import java.util.UUID

@Parcelize
data class Picture(
    override val uuid: UUID = UUID.randomUUID(),
    val url: String,
    val date: LocalDate,
    val favorite: Boolean = false
) : ItemData(uuid), Parcelable, Comparable<Picture> {
    override fun compareTo(other: Picture): Int {
        return when (val compareDate = this.date.compareTo(other.date) * (-1)) {
            0 -> this.uuid.compareTo(other.uuid)
            else -> return compareDate
        }
    }
}