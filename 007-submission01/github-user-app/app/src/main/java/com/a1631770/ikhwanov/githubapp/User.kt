package com.a1631770.ikhwanov.githubapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String?,
    var name: String?,
    var company: String?,
    var location: String?,
    var repo: Int?,
    var follower: Int?,
    var following: Int?

): Parcelable {
}