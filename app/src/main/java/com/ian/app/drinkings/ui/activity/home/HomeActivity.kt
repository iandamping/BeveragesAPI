package com.ian.app.drinkings.ui.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel
import com.ian.app.drinkings.helper.fullScreenAnimation
import com.ian.app.drinkings.helper.gone
import com.ian.app.drinkings.helper.loadUrl
import com.ian.app.drinkings.helper.loadUrlResize
import com.ian.recyclerviewhelper.helper.setUpHorizontal
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeView {


    private val vm: GetAllDrinksViewModel by viewModel()
    private lateinit var presenter: HomePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(vm).apply {
            attachView(this@HomeActivity, this@HomeActivity)
            onCreate()
        }
    }

    override fun getAlchoholDrink(data: List<Drinks>?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
        data?.let { nonNullData ->
            rvAlchoholDrink.setUpHorizontal(nonNullData, R.layout.item_home, {
                with(this) {
                    tvHomeDrinkName.text = it.strDrink
                    ivHomeDrink.loadUrl(it.strDrinkThumb)
                }
            })
        }

    }

    override fun getNonAlcoholDrink(data: List<Drinks>?) {
        data?.let { nonNullData ->
            rvNonAlchoholDrink.setUpHorizontal(nonNullData, R.layout.item_home, {
                with(this) {
                    tvHomeDrinkName.text = it.strDrink
                    ivHomeDrink.loadUrl(it.strDrinkThumb)
                }
            })
        }
    }

    override fun getRandomDrink(data: Drinks?) {
        ivRandomDrink.loadUrlResize(data?.strDrinkThumb)

    }

    override fun onFailedGetDrink(msg: String?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
    }


    override fun initView() {
    }

    override fun onPause() {
        super.onPause()
        shimmerHome?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmerHome?.startShimmer()
    }

}
