package com.ian.app.drinkings.ui.activity.detail

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetDetailDrinkData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.viewmodel.GetDetailDrinkViewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DetailDrinkPresenter(private val vm: GetDetailDrinkViewModel) : BasePresenter<DetailDrinkView>() {

    override fun onCreate() {
        view()?.initView()
    }

    fun getDetailDrink(drinkID: String) {
        vm.getDetailDrink(drinkID).apply {
            vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                when (it) {
                    is OnSuccessGetData -> setDialogShow(it.show)
                    is OnGetDetailDrinkData -> view()?.onSuccesGetData(it.data)
                    is OnFailedGetData -> view()?.onFailGetData(it.msg)
                }
            })
        }
    }
}