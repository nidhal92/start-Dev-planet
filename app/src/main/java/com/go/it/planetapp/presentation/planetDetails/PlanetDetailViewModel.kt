package com.go.it.planetapp.presentation.planetDetails

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.go.it.planetapp.base.BaseViewModel
import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.domain.useCases.GetPlanetDetailUseCase
import com.go.it.planetapp.domain.useCases.GetPlanetListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PlanetDetailViewModel : BaseViewModel<PlanetDetailNavigator>() {
    private  var getPlanetDetailUseCase: GetPlanetDetailUseCase = GetPlanetDetailUseCase()
    private var planet = ObservableField<Planet>()
    val progressVisible = ObservableBoolean()
    val containerVisible = ObservableBoolean()
    fun getPlanet(): ObservableField<Planet>? {
        Log.d("PLANET_FIELDS",planet.get()?.climate.toString())
        return planet
    }


    private fun handleData(result: GetPlanetDetailUseCase.Result?) {
        progressVisible.set(result == GetPlanetDetailUseCase.Result.Loading)
        containerVisible.set(false)
        when (result) {

            is GetPlanetDetailUseCase.Result.Success -> {
                planet.set(result.planet)
                planet.notifyChange()
                containerVisible.set(true)

            }
            is GetPlanetDetailUseCase.Result.Failure -> {
                Log.d("ERROR", result.throwable.message)
                containerVisible.set(false)
            }
        }
    }

    fun backClick() {

        getNavigator()?.goBack()
    }

    fun findPlanet(id: Int?) {
        getPlanetDetailUseCase.execute(id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                handleData(it)
            }
    }

}