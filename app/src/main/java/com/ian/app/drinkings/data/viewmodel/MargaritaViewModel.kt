package com.ian.app.drinkings.data.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BaseViewModel
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.localdata.CocktailDrink
import com.ian.app.drinkings.data.localdata.MyDrinkingDatabase
import com.ian.app.drinkings.data.model.GenericViewModelWithLiveData
import com.ian.app.drinkings.data.repo.MargaritaRepo
import com.ian.app.drinkings.helper.asyncRxExecutor
import com.ian.app.drinkings.helper.customViewModelFactoriesHelper
import com.ian.app.drinkings.helper.doSomethingIOScope
import com.ian.app.drinkings.helper.executes

/**
 *
Created by Ian Damping on 25/05/2019.
Github = https://github.com/iandamping
 */
class MargaritaViewModel(private val repo: MargaritaRepo, private val db: MyDrinkingDatabase) : BaseViewModel() {

    fun getMargaritaData(lifeCycle: FragmentActivity) {
        lifeCycle.customViewModelFactoriesHelper({ GenericViewModelWithLiveData(db.drinkDao().loadAllLocalData()) }) {
            this.getGenericViewModelData()?.observe(lifeCycle, Observer {
                liveDataState.value = OnSuccessGetData(false)
                compose.executes(repo.getMargaritas(), { failed ->
                    liveDataState.value = OnSuccessGetData(true)
                    liveDataState.value = OnFailedGetData(failed.localizedMessage)
                }, { success ->
                    liveDataState.value = OnSuccessGetData(true)
                    success?.let { nonNullData ->
                        if (it == null) {
                           /* //coroutine way
                            uiScope.doSomethingIOScope {
                                db.drinkDao().insertLocalData(nonNullData)
                            }*/
                            //rxjava2 way
                            compose.asyncRxExecutor {
                                db.drinkDao().insertLocalData(nonNullData)
                            }
                        }
                        if (nonNullData.containsAll(it)) {
                          /*  //coroutine way
                            uiScope.doSomethingIOScope {
                                db.drinkDao().deleteAllLocalData()
                                db.drinkDao().insertLocalData(nonNullData)
                            }*/
                            //rxjava2 way
                            compose.asyncRxExecutor {
                                db.drinkDao().deleteAllLocalData()
                                db.drinkDao().insertLocalData(nonNullData)
                            }
                        }
                    }
                })
                liveDataState.value = OnGetData<List<CocktailDrink.Drink>>(it)
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposeComposite()
        fetchingJob.cancel()
    }
}
