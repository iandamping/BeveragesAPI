package com.ian.app.drinkings.ui.activity.discover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetDiscoverDrinkViewModel
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDetail
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDiscover
import com.ian.app.drinkings.ui.activity.detail.DetailDrinkActivity
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.recyclerviewhelper.helper.setUpWithGrid
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.item_discover_drinks.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DiscoverActivity : AppCompatActivity(), DiscoverView {
    private val vm: GetDiscoverDrinkViewModel by viewModel()
    private lateinit var presenter: DiscoverPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_discover)
        presenter = DiscoverPresenter(vm).apply {
            attachView(this@DiscoverActivity, this@DiscoverActivity)
            onCreate()
            getDiscoverData(intent.getStringExtra(intentKeyToDiscover))
        }
    }

    override fun onSuccessGetData(data: List<Drinks>?) {
        data?.let { nonNullData ->
            rvDiscoverDrink.setUpWithGrid(nonNullData, R.layout.item_discover_drinks, 2, {
                with(this) {
                    ivDiscoverDrink.loadResizeWithGlide(it.strDrinkThumb, this@DiscoverActivity)
                    tvDiscoverDrinkCategory.text = it.strDrink
                }
            }, {
                startActivity<DetailDrinkActivity> {
                    putExtra(intentKeyToDetail, idDrink)
                }
            })


        }
    }

    override fun onFailGetData(msg: String?) {
    }

    override fun initView() {
    }
}