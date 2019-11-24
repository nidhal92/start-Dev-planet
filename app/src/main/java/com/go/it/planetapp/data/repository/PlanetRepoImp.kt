package com.go.it.planetapp.data.repository


import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.entities.PlanetListResponse
import com.go.it.planetapp.data.remote.RetrofitClient
import com.go.it.planetapp.domain.repository.IPlanetRepository
import io.reactivex.Observable

class PlanetRepoImp  : IPlanetRepository {

    override fun getPlanetList(page:String) : Observable<PlanetListResponse> {
        return RetrofitClient.build().getListPlanet(page).map { it }

    }

    override fun getPlanetDetail(id:String): Observable<Planet> {
        return RetrofitClient.build().getPlanet(id).map { it }

    }
}