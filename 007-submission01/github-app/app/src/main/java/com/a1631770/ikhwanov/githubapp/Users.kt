package com.a1631770.ikhwanov.githubapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    var avatar: Int? = 0,
    var name: String? = "",
    var username: String? = "",
    var company: String? = "",
    var location: String? = "",
    var repo: String? = "",
    var follower: String? = "",
    var following: String? = ""
) : Parcelable {
}