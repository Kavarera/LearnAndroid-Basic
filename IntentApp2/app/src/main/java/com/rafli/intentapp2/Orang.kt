package com.rafli.intentapp2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Orang (
    val Nama: String?,
    val Umur: Int?,
    val Email: String?,
    val Kota: String?

        ):Parcelable