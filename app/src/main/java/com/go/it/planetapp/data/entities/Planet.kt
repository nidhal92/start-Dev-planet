package com.go.it.planetapp.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Planet(
    @SerializedName("name") var name: String,
    @SerializedName("population") var population: String,
    @SerializedName("diameter") var diameter: String,
    @SerializedName("rotation_period") var rotation_period: String,
    @SerializedName("orbital_period") var orbital_period: String,
    @SerializedName("climate") var climate: String,
    @SerializedName("gravity") var gravity: String,
    @SerializedName("terrain") var terrain: String,
    @SerializedName("surface_water") var surface_water: String
) : Serializable