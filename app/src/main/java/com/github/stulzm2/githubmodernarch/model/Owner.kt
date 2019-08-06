package com.github.stulzm2.githubmodernarch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner (

    @Json(name="login")
    var login: String?,
    @Json(name="id")
    var id: Long?,
    @Json(name="avatar_url")
    var avatar_url: String?

)