package com.github.stulzm2.githubmodernarch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item (

    @Json(name="id")
    var id: String?,
    @Json(name="name")
    var name: String?,
    @Json(name="full_name")
    var full_name: String?,
    @Json(name="owner")
    var owner: Owner,
    @Json(name="private")
    var private: Boolean,
    @Json(name="html_url")
    var html_url: String?,
    @Json(name="description")
    var description: String?

)