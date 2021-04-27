package com.a1631770.ikhwanov.github_user2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserItem(
    var avatar: String? = "",
    var username: String? = "",
    var name: String? = "",
    var location: String? = "",
    var company: String? = "",
    var gitUrl: String? = "",
    var follower: Int? = 0,
    var following: Int? = 0,
    var repository: Int? = 0

): Parcelable
