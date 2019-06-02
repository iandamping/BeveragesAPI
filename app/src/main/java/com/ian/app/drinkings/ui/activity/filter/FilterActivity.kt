package com.ian.app.drinkings.ui.activity.filter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.DrinkingsApp.Companion.gson
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetFilterDrinksViewModel
import com.ian.app.drinkings.helper.BeverageConstant
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToFilter
import com.ian.app.drinkings.ui.activity.discover.DiscoverActivity
import com.ian.app.helper.util.*
import com.ian.recyclerviewhelper.helper.setUpWithGrid
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.item_filter.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 01/06/2019.
Github = https://github.com/iandamping
 */
class FilterActivity : AppCompatActivity(), FilterView {
    private val vm: GetFilterDrinksViewModel by viewModel()
    private lateinit var presenter: FilterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_filter)
        presenter = FilterPresenter(vm).apply {
            attachView(this@FilterActivity, this@FilterActivity)
            onCreate()
            onGetFilterData(intent.getStringExtra(intentKeyToFilter))
        }
    }

    override fun onGetFilterData(data: List<Drinks>?) {
        shimmerFilter?.stopShimmer()
        shimmerFilter?.gone()
        rvInformationFilter.setUpWithGrid(data, R.layout.item_filter, 3, {
            with(this) {
                when {
                    !it.strGlass.isNullOrEmpty() -> {
                        if (it.strGlass.length >= 12) {
                            val tmp = it.strGlass.substring(0, 12) + " ..."
                            tvDescriptionFilter.text = tmp
                        } else {
                            tvDescriptionFilter.text = it.strGlass
                        }
                        ivDescriptionFilter.loadResizeWithGlide(
                                resources.getString(R.string.ingredient_images_helper) + it.strGlass + "-Small.png",
                                this@FilterActivity
                        )

                    }
                    !it.strCategory.isNullOrEmpty() -> {
                        if (it.strCategory.length >= 12) {
                            val tmp = it.strCategory.substring(0, 12) + " ..."
                            tvDescriptionFilter.text = tmp
                        } else {
                            tvDescriptionFilter.text = it.strCategory
                        }
                        ivDescriptionFilter.loadResizeWithGlide(
                                resources.getString(R.string.ingredient_images_helper) + it.strCategory + "-Small.png",
                                this@FilterActivity
                        )
                    }
                    !it.strIngredient1.isNullOrEmpty() -> {
                        if (it.strIngredient1.length >= 12) {
                            val tmp = it.strIngredient1.substring(0, 12) + " ..."
                            tvDescriptionFilter.text = tmp
                        } else {
                            tvDescriptionFilter.text = it.strIngredient1
                        }
                        ivDescriptionFilter.loadResizeWithGlide(
                                resources.getString(R.string.ingredient_images_helper) + it.strIngredient1 + "-Small.png",
                                this@FilterActivity
                        )
                    }
                }
            }
        }, {
            startActivity<DiscoverActivity> {
                putExtra(BeverageConstant.intentKeyToDiscoverToGetData, gson.toJson(this@setUpWithGrid))
            }
        })


    }

    override fun onFailGetFilterData(msg: String?) {
        shimmerFilter?.stopShimmer()
        shimmerFilter?.gone()
        logE(msg)
    }

    override fun initView() {
    }

    override fun onPause() {
        super.onPause()
        shimmerFilter?.stopShimmer()
    }

    override fun onResume() {
        super.onResume()
        shimmerFilter?.startShimmer()
    }
}