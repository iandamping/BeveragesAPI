package com.ian.app.drinkings.ui.activity.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetDetailDrinkViewModel
import com.ian.app.drinkings.helper.Constant.intentKeyToDetail
import com.ian.app.helper.util.fullScreen
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.loadUrl
import kotlinx.android.synthetic.main.activity_detail_drink.*
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
            ivDetailedDrink.loadUrl(nonNullData.strDrinkThumb)
            ivDetailedDrink.setOnClickListener {
                fullScreen(nonNullData.strDrinkThumb)
            }
            tvDetailedDrinkCategory.text = "Drink category : ${nonNullData.strCategory}"
            tvDetailedDrinkArea.text = "Common Drink in ${nonNullData.strArea}"
            tvDetailedDrinkInstruction.text = nonNullData.strInstructions
            toolbarDetailed.title = nonNullData.strDrink
        }

    }

    override fun onFailGetData(msg: String?) {
    }

    override fun initView() {
    }

}