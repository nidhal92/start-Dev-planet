package com.go.it.planetapp.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

 class PlanetListResponse (
    @SerializedName("count") var count: String,
    @SerializedName("next") var nextPage: String?,
    @SerializedName("previous") var previousPage: String,
    @SerializedName("results") var results: ArrayList<Planet>
    ):Serializable