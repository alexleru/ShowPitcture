package com.alexleru.showpitcture.domain.entity

import java.util.UUID

// TODO: Перенести в presentation; для domain - сделать Domain Model.
// На уровне presentation сделать маппер domain -> presentation
sealed class ItemData(open val uuid: UUID)
