package com.ian.app.drinkings.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.localdata.CocktailDrink
import com.ian.app.drinkings.data.viewmodel.MargaritaViewModel
import com.ian.app.drinkings.helper.logE
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainView {

    private val vm: MargaritaViewModel by viewModel()
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(vm).apply {
            attachView(this@MainActivity, this@MainActivity)
            onCreate()
        }
    }

    override fun showMargaritaData(data: List<CocktailDrink.Drink>?) {
    }

    override fun initView() {
    }

}
