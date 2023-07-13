package com.alexleru.showpictrure.presentation.view.modelView.arch

import android.os.Message

sealed class PicturesEvent {
    class ShowToast(val message: Message): PicturesEvent()
}
