package com.go.it.planetapp.presentation.planetDetails


import androidx.lifecycle.ViewModelProviders
import com.go.it.planetapp.R
import com.go.it.planetapp.base.BaseActivity
import com.go.it.planetapp.databinding.ActivityPlanetDetailBinding
import com.go.it.planetapp.presentation.planetList.PlanetListActivity

class PlanetDetailActivity : BaseActivity<ActivityPlanetDetailBinding>(), PlanetDetailNavigator {

    private lateinit var model: PlanetDetailViewModel



    override fun setViewModel() {
        model = ViewModelProviders.of(this!!).get(PlanetDetailViewModel::class.java)
    }

    override fun init() {
        model.setNavigator(this)
        getDataBinding()?.viewModel = model
        model.findPlanet(intent.extras?.getInt(PlanetListActivity.ITEM_ID))

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_planet_detail
    }

    override fun goBack() {
        onBackPressed()
    }
}
