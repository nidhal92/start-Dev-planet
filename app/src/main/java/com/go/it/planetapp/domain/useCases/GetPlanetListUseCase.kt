package com.go.it.planetapp.domain.useCases


import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.entities.PlanetListResponse
import com.go.it.planetapp.data.repository.PlanetRepoImp
import com.go.it.planetapp.domain.repository.IPlanetRepository
import io.reactivex.Observable


class GetPlanetListUseCase {
    private val repository: IPlanetRepository = PlanetRepoImp()
    sealed class Result {
        object Loading : Result()
        data class Success(val planetResponse: PlanetListResponse) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(page: String): Observable<Result> {
        return repository.getPlanetList(page)
            .doOnNext {Result.Success(it)   }
            .map { Result.Success(it) as Result }
            .onErrorReturn { Result.Failure(it) }
            .startWith(Result.Loading)

    }
}