package com.example.mygithubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubRepository(
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forks: Int? = null,
) : Parcelable