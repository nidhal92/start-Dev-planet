package com.go.it.planetapp.presentation.planetList.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.go.it.planetapp.base.BaseViewHolder
import com.go.it.planetapp.base.ObservableRecyclerViewAdapter
import com.go.it.planetapp.data.entities.Planet
import com.go.it.planetapp.databinding.ItemPlanetListPlanetActivityBinding
import com.go.it.planetapp.presentation.planetList.PlanetListViewModel


class PlanetListAdapter(var viewModel: PlanetListViewModel, context: Context):
    ObservableRecyclerViewAdapter<Planet, BaseViewHolder<Planet>>(viewModel.getPlanetList()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Planet> {
        return PlanetHolder(
            ItemPlanetListPlanetActivityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Planet>, position: Int) {
        holder.bind(getItem(position))
    }
    inner class PlanetHolder(
        private val binding: ItemPlanetListPlanetActivityBinding

    ) : BaseViewHolder<Planet>(binding.root) {
        override fun bind(item: Planet) {

            binding.planet = item
            binding.holder=this
        }
       fun navigateToPlanetDetailActivityClick(item: Planet){
            viewModel.navigateToPlanetDetailActivity(viewModel.getPlanetList().indexOf(item)+1)
       }


    }
}