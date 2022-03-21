package com.rafli.submission1_githubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
        val photo: Int?,
        val username: String?,
        val nama: String?,
        val lokasi: String?,
        val follower: String?,
        val following: String?,
        val company: String?,
        val repo:String?,
        var favorited: String?
):Parcelable