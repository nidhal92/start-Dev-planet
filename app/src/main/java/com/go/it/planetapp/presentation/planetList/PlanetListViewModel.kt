package com.go.it.planetapp.presentation.planetList

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.go.it.planetapp.base.BaseViewModel
import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.data.entities.PlanetListResponse
import com.go.it.planetapp.domain.useCases.GetPlanetListUseCase
import com.go.it.planetapp.utils.UriUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


@SuppressLint("CheckResult")
class PlanetListViewModel : BaseViewModel<PlanetListNavigator>() {
    private val map: HashMap<String, String> = HashMap<String, String>()
    private lateinit var planetResponse: PlanetListResponse
    private var getPlanetListUseCase: GetPlanetListUseCase = GetPlanetListUseCase()
    private var planetArray = ObservableArrayList<Planet>()
    val progressVisible = ObservableBoolean()
    fun getPlanetList(): ObservableArrayList<Planet> {
        return planetArray
    }

    init {
        getPlanetListUseCase.execute("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe {
                handleData(it)
            }

    }

    private fun addUniqueItems(planetList: ArrayList<Planet>) {

        for (item in planetList) {
            if (!map.containsKey(item.name)) {
                map[item.name] = item.name
                planetArray.add(item)
            }
        }
    }


    private fun handleData(result: GetPlanetListUseCase.Result?) {
        progressVisible.set(result == GetPlanetListUseCase.Result.Loading)
        when (result) {

            is GetPlanetListUseCase.Result.Success -> {

                planetResponse = result.planetResponse
                addUniqueItems(result.planetResponse.results)


            }
            is GetPlanetListUseCase.Result.Failure -> {
                Log.d("ERROR", result.throwable.message)
            }
        }
    }

    fun navigateToPlanetDetailActivity(planetIndex: Int) {

        getNavigator()?.navigateToPlanetDetail(planetIndex)
    }


    fun getNextPage() {
        if (planetResponse.nextPage != null)
            getPlanetListUseCase.execute(UriUtils.getQueryFromUri(planetResponse.nextPage, "page"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    handleData(it)
                }
    }

}