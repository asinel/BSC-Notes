package com.sinelnikov.bsc.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PublishedNote(val id: Int, val title: String): Parcelable