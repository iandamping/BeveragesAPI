package com.ian.app.drinkings.ui.activity.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetDetailDrinkViewModel
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDetail
import com.ian.app.helper.util.fullScreen
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.loadWithGlide
import com.ian.recyclerviewhelper.helper.setUpVertical
import kotlinx.android.synthetic.main.activity_detail_drink.*
import kotlinx.android.synthetic.main.item_ingredient_adapter.view.*
import kotlinx.android.synthetic.main.item_measurement_adapter.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DetailDrinkActivity : AppCompatActivity(), DetailDrinkView {

    private lateinit var presenter: DetailDrinkPresenter
    private val vm: GetDetailDrinkViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_detail_drink)
        presenter = DetailDrinkPresenter(vm).apply {
            attachView(this@DetailDrinkActivity, this@DetailDrinkActivity)
            onCreate()
            getDetailDrink(intent.getStringExtra(intentKeyToDetail))
        }
    }

    override fun onSuccesGetData(data: Drinks?) {
        data?.let { nonNullData ->
            ivDetailedDrink.loadWithGlide(nonNullData.strDrinkThumb, this)
            ivDetailedDrink.setOnClickListener {
                fullScreen(nonNullData.strDrinkThumb)
            }
            tvDetailedDrinkCategory.text = "Drink category : ${nonNullData.strCategory}"
            tvDetailedDrinkArea.text = "Usually serve with ${nonNullData.strGlass}"
            tvDetailedDrinkInstruction.text = nonNullData.strInstructions
            toolbarDetailed.title = nonNullData.strDrink
        }

    }

    override fun onFailGetData(msg: String?) {
    }

    override fun initView() {
    }

    override fun onShowIngredientData(dataIngredient: List<String>, dataMeasurement: List<String>) {
        rvDetailedIngredients.isNestedScrollingEnabled = false
        rvDetailedMeasurement.isNestedScrollingEnabled = false
        dataIngredient.let { data ->
            rvDetailedIngredients.setUpVertical(data, R.layout.item_ingredient_adapter, {
                with(this) {
                    tvIngredientAdapter.text = it
                }
            })
        }
        dataMeasurement.let { data ->
            rvDetailedMeasurement.setUpVertical(data, R.layout.item_measurement_adapter, {
                with(this) {
                    tvMeasurementAdapter.text = it
                }
            })
        }
    }


}