package com.a1631770.ikhwanov.githubapp

data class Users(
    var avatar: Int,
    var name: String,
    var username: String,
    var company: String,
    var location: String,
    var repo: String,
    var follower: String,
    var following: String
) {
}