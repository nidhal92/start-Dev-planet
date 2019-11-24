package com.go.it.planetapp.domain.repository

import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.entities.PlanetListResponse
import io.reactivex.Observable

interface IPlanetRepository {
    fun getPlanetList(page: String): Observable<PlanetListResponse>
    fun getPlanetDetail(id: String): Observable<Planet>
}