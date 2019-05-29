package com.ian.app.drinkings.data.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.localdata.local_model.AlchoholDrink
import com.ian.app.drinkings.data.localdata.MyDrinkingDatabase
import com.ian.app.drinkings.data.localdata.local_model.NonAlchoholDrink
import com.ian.app.drinkings.data.model.GenericViewModelZipperPair
import com.ian.app.drinkings.data.repo.AllDrinksRepo
import com.ian.app.drinkings.helper.asyncRxExecutor
import com.ian.app.drinkings.helper.customViewModelFactoriesHelper
import com.ian.app.drinkings.helper.executes
import com.ian.app.drinkings.helper.obsWithSecondZip

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class GetAllDrinksViewModel(private val repo: AllDrinksRepo, private val db: MyDrinkingDatabase) : BaseViewModel() {

    fun getDrinksData(lifeCycle: FragmentActivity) {
        lifeCycle.customViewModelFactoriesHelper({
            GenericViewModelZipperPair(
                    db.alchoholDrinkDao().loadAllLocalData(),
                    db.nonAlchoholDrinkDao().loadAllLocalData()
            )
        }) {
            liveDataState.value = OnSuccessGetData(false)
            with(this) {
                getGenericData().observe(lifeCycle, Observer { dataFromLocal ->
                    if (dataFromLocal.first.isNotEmpty() && dataFromLocal.second.isNotEmpty()) {
                        liveDataState.value = OnSuccessGetData(true)
                        liveDataState.value = OnGetData<Pair<List<AlchoholDrink>?, List<NonAlchoholDrink>?>>(dataFromLocal)
                    }
                    if (dataFromLocal.first.isEmpty() && dataFromLocal.second.isEmpty()) {
                        compose.executes(obsWithSecondZip(repo.getAlchoholDrink(), repo.getNonAlchoholDrink()), { failed ->
                            liveDataState.value = OnSuccessGetData(true)
                            liveDataState.value = OnFailedGetData(failed?.localizedMessage)
                        }, { success ->
                            success?.let { nonNullNetworkData ->
                                compose.asyncRxExecutor {
                                    db.alchoholDrinkDao().insertLocalData(nonNullNetworkData.first)
                                    db.nonAlchoholDrinkDao().insertLocalData(nonNullNetworkData.second)
                                }
                            }
                        })
                    }
                })

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposeComposite()
    }
}
