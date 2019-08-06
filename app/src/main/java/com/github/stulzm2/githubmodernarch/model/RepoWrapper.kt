package com.github.stulzm2.githubmodernarch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoWrapper (

    @Json(name="items")
    var items: MutableList<Item>?

)