package com.go.it.planetapp.presentation.planetList

import android.content.Intent
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.go.it.planetapp.R
import com.go.it.planetapp.base.BaseActivity
import com.go.it.planetapp.databinding.ActivityPlanetListBinding
import com.go.it.planetapp.presentation.planetDetails.PlanetDetailActivity
import com.go.it.planetapp.presentation.planetList.adapters.PlanetListAdapter
import kotlin.properties.Delegates
import com.go.it.planetapp.presentation.planetList.PlanetListActivity.OnRecyclerScroll as OnRecyclerScroll1


class PlanetListActivity : BaseActivity<ActivityPlanetListBinding>(), PlanetListNavigator {




    private lateinit var model: PlanetListViewModel
    private var isLoading by Delegates.notNull<Boolean>()
    override fun setViewModel() {
        model = ViewModelProviders.of(this!!).get(PlanetListViewModel::class.java)
    }

    override fun init() {
        model.setNavigator(this)
        getDataBinding()?.viewModel = model
        getDataBinding()?.activityPlanetListRecycler?.addOnScrollListener(OnRecyclerScroll())
        isLoading = model.progressVisible.get()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_planet_list
    }

    companion object {
        final const val ITEM_ID = "ITEM_ID"
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindList(recyclerView: RecyclerView, viewModel: PlanetListViewModel) {

            val adapter = PlanetListAdapter(viewModel, recyclerView.context)

            recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, 0))
            val manager = LinearLayoutManager(recyclerView.context)
            manager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
    }

    override fun navigateToPlanetDetail(planetIndex: Int) {
        startActivity(
            Intent(this@PlanetListActivity, PlanetDetailActivity::class.java).putExtra(
                ITEM_ID, planetIndex
            )
        )
    }

    inner class OnRecyclerScroll : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            var visibleItemCount = (recyclerView.layoutManager as LinearLayoutManager?)!!.childCount
            var totalItemCount = (recyclerView.layoutManager as LinearLayoutManager?)!!.itemCount
            var firstVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()


            if (!isLoading ) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= 10
                ) {
                    model.getNextPage()


                }
            }
        }

    }

    override fun onBackPressed() {
        if ((getDataBinding()?.activityPlanetListRecycler?.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition() > 0) {
            getDataBinding()?.activityPlanetListRecycler?.smoothScrollToPosition(0)

        } else
            super.onBackPressed()


    }


}
