package com.ian.app.drinkings.data.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.ian.app.drinkings.api.ApiInterface
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.MyDrinkingDatabase
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import com.ian.app.drinkings.data.viewmodel.model.GenericViewModelZipperPair
import com.ian.app.drinkings.helper.customViewModelFactoriesHelper
import com.ian.app.drinkings.helper.deferredPair
import com.ian.app.drinkings.helper.doSomethingWithIOScope

/**
 *
Created by Ian Damping on 28/05/2019.
Github = https://github.com/iandamping
 */
class GetAllDrinksCoroutineViewModel(private val api: ApiInterface, private val db: MyDrinkingDatabase) : BaseViewModel() {

    fun getDrinksData(lifeCycle: FragmentActivity) {
        lifeCycle.customViewModelFactoriesHelper({
            GenericViewModelZipperPair(db.alchoholDrinkDao().loadAllLocalData(),
                    db.nonAlchoholDrinkDao().loadAllLocalData())
        }) {
            liveDataState.value = OnSuccessGetData(false)
            with(this) {
                getGenericData().observe(lifeCycle, Observer { dataFromLocal ->
                    if (dataFromLocal.first.isNotEmpty() && dataFromLocal.second.isNotEmpty()) {
                        liveDataState.value = OnSuccessGetData(true)
                        liveDataState.value = OnGetData<Pair<List<AlchoholDrink>?, List<NonAlchoholDrink>?>>(dataFromLocal)
                    }
                    if (dataFromLocal.first.isEmpty() && dataFromLocal.second.isEmpty()) {
                        uiScope.deferredPair(Pair(api.getNonAlchoholicDrinksDef(), api.getAlchoholicDrinksDef()), { first, second ->
                            uiScope.doSomethingWithIOScope {
                                db.nonAlchoholDrinkDao().insertLocalData(first.cocktailDrinks)
                                db.alchoholDrinkDao().insertLocalData(second.cocktailDrinks)
                            }
                        }) {
                            liveDataState.value = OnSuccessGetData(true)
                            liveDataState.value = OnFailedGetData(it)
                        }
                    }


                })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchingJob.cancel()
    }
}