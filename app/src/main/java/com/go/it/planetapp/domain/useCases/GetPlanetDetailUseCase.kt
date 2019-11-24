package com.go.it.planetapp.domain.useCases


import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.repository.PlanetRepoImp
import com.go.it.planetapp.domain.repository.IPlanetRepository
import io.reactivex.Observable


class GetPlanetDetailUseCase {
    private val repository: IPlanetRepository = PlanetRepoImp()
    sealed class Result {
        object Loading : Result()
        data class Success(val planet: Planet) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(id: String): Observable<Result> {
        return repository.getPlanetDetail(id)
            .doOnNext {Result.Success(it) as Result  }
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)

    }
}