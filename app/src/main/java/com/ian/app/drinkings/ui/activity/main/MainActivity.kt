package com.ian.app.drinkings.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import com.ian.app.drinkings.data.viewmodel.GetAllDrinksCoroutineViewModel
import com.ian.app.drinkings.helper.logE
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainView {
    private val vm: GetAllDrinksCoroutineViewModel by viewModel()
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(vm).apply {
            attachView(this@MainActivity, this@MainActivity)
            onCreate()
        }
    }

    override fun getDrinksData(data: Pair<List<AlchoholDrink>?, List<NonAlchoholDrink>?>) {
        data.first?.forEach {
            logE(it.idMeal + " Get the first fuckin data")
        }
        data.second?.forEach {
            logE(it.idMeal + " Get the second fuckin data")
        }
    }

    override fun initView() {
    }

}
