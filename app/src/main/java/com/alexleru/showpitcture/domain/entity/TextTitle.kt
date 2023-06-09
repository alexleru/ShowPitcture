package com.alexleru.showpitcture.domain.entity

import java.util.UUID

data class TextTitle
    (
    override val uuid: UUID = UUID.randomUUID(),
    val text: String,
    val position: Int
): ItemData(uuid)
