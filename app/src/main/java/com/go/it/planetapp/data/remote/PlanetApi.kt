package com.go.it.planetapp.data.remote


import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.entities.PlanetListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetApi {
    @GET(ApiEndPoints.GET_PLANET_LIST)
    fun getListPlanet(@Query("page") page:String):Observable<PlanetListResponse>
    @GET(ApiEndPoints.GET_PLANET)
    fun getPlanet(@Path("id") id:String):Observable<Planet>
}