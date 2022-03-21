package com.rafli.myintentapp

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@Parcelize

data class Person (
    val Name: String?,
    val Age: Int?,
    val Email: String?,
    val City: String?
) : Parcelable