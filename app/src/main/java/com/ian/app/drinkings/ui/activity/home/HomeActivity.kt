package com.ian.app.drinkings.ui.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksViewModel
import com.ian.app.drinkings.helper.BeverageConstant.alchoholState
import com.ian.app.drinkings.helper.BeverageConstant.filterByCategory
import com.ian.app.drinkings.helper.BeverageConstant.filterByGlass
import com.ian.app.drinkings.helper.BeverageConstant.filterByIngredient
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDetail
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDiscover
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToFilter
import com.ian.app.drinkings.helper.BeverageConstant.nonAlchoholState
import com.ian.app.drinkings.helper.BeverageConstant.optionalAlchoholState
import com.ian.app.drinkings.ui.activity.detail.DetailDrinkActivity
import com.ian.app.drinkings.ui.activity.discover.DiscoverActivity
import com.ian.app.drinkings.ui.activity.filter.FilterActivity
import com.ian.app.helper.util.*
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
                    ivHomeDrink.loadResizeWithGlide(it.strDrinkThumb, this@HomeActivity)
                }
            }, {
                startActivity<DetailDrinkActivity> {
                    putExtra(intentKeyToDetail, idDrink)
                }
            })
        }

    }

    override fun getNonAlcoholDrink(data: List<Drinks>?) {
        data?.let { nonNullData ->
            rvNonAlchoholDrink.setUpHorizontal(nonNullData, R.layout.item_home, {
                with(this) {
                    tvHomeDrinkName.text = it.strDrink
                    ivHomeDrink.loadResizeWithGlide(it.strDrinkThumb, this@HomeActivity)
                }
            }, {
                startActivity<DetailDrinkActivity> {
                    putExtra(intentKeyToDetail, idDrink)
                }
            })
        }
    }

    override fun getOptionalAlchoholDrink(data: List<Drinks>?) {
        data?.let { nonNullData ->
            rvOptionalAlchoholDrink.setUpHorizontal(nonNullData, R.layout.item_home, {
                with(this) {
                    tvHomeDrinkName.text = it.strDrink
                    ivHomeDrink.loadResizeWithGlide(it.strDrinkThumb, this@HomeActivity)
                }
            }, {
                startActivity<DetailDrinkActivity> {
                    putExtra(intentKeyToDetail, idDrink)
                }
            })
        }
    }


    override fun getRandomDrink(data: Drinks?) {
        ivRandomDrink.loadUrlResize(data?.strDrinkThumb)
        lnIcon1.setOnClickListener {
            startActivity<DetailDrinkActivity> {
                putExtra(intentKeyToDetail, data?.idDrink)
            }
        }
    }

    override fun onFailedGetDrink(msg: String?) {
        shimmerHome?.stopShimmer()
        shimmerHome?.gone()
    }


    override fun initView() {
        tvSeeAllNonAlchoholDrink.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentKeyToDiscover, nonAlchoholState)
            }
        }
        tvSeeAllAlchoholDrink.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentKeyToDiscover, alchoholState)
            }
        }
        tvSeeAllOptionalAlchoholDrink.setOnClickListener {
            startActivity<DiscoverActivity> {
                putExtra(intentKeyToDiscover, optionalAlchoholState)
            }
        }
        lnIcon2.setOnClickListener {
            startActivity<FilterActivity> {
                putExtra(intentKeyToFilter, filterByCategory)
            }
        }
        lnIcon3.setOnClickListener {
            startActivity<FilterActivity> {
                putExtra(intentKeyToFilter, filterByGlass)
            }
        }
        lnIcon4.setOnClickListener {
            startActivity<FilterActivity> {
                putExtra(intentKeyToFilter, filterByIngredient)
            }
        }
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
