package com.ian.app.drinkings.ui.activity.detail

import androidx.lifecycle.Observer
import com.ian.app.drinkings.base.BasePresenter
import com.ian.app.drinkings.base.OnFailedGetData
import com.ian.app.drinkings.base.OnGetDetailDrinkData
import com.ian.app.drinkings.base.OnSuccessGetData
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.data.viewmodel.GetDetailDrinkViewModel

/**
 *
Created by Ian Damping on 31/05/2019.
Github = https://github.com/iandamping
 */
class DetailDrinkPresenter(private val vm: GetDetailDrinkViewModel) : BasePresenter<DetailDrinkView>() {
    private var listIngredient: MutableList<String> = mutableListOf()
    private var listMeasurement: MutableList<String> = mutableListOf()
    override fun onCreate() {
        view()?.initView()
    }

    fun getDetailDrink(drinkID: String) {
        vm.getDetailDrink(drinkID).apply {
            vm.liveDataState.observe(getLifeCycleOwner(), Observer {
                when (it) {
                    is OnSuccessGetData -> setDialogShow(it.show)
                    is OnGetDetailDrinkData -> {
                        view()?.onSuccesGetData( it.data)

                        it.data?.let { nonNullData -> extractIngredientData(nonNullData) }
                    }
                    is OnFailedGetData -> view()?.onFailGetData(it.msg)
                }
            })
        }
    }

    private fun extractIngredientData(data: Drinks) {
        listIngredient.run {
            if (!data.strIngredient1.equals("")) data.strIngredient1?.let { add(it) }
            if (!data.strIngredient2.equals("")) data.strIngredient2?.let { add(it) }
            if (!data.strIngredient3.equals("")) data.strIngredient3?.let { add(it) }
            if (!data.strIngredient4.equals("")) data.strIngredient4?.let { add(it) }
            if (!data.strIngredient5.equals("")) data.strIngredient5?.let { add(it) }
            if (!data.strIngredient6.equals("")) data.strIngredient6?.let { add(it) }
            if (!data.strIngredient7.equals("")) data.strIngredient7?.let { add(it) }
            if (!data.strIngredient8.equals("")) data.strIngredient8?.let { add(it) }
            if (!data.strIngredient9.equals("")) data.strIngredient9?.let { add(it) }
            if (!data.strIngredient10.equals("")) data.strIngredient10?.let { add(it) }
            if (!data.strIngredient11.equals("")) data.strIngredient11?.let { add(it) }
            if (!data.strIngredient12.equals("")) data.strIngredient12?.let { add(it) }
            if (!data.strIngredient13.equals("")) data.strIngredient13?.let { add(it) }
            if (!data.strIngredient14.equals("")) data.strIngredient14?.let { add(it) }
            if (!data.strIngredient15.equals("")) data.strIngredient15?.let { add(it) }

        }
        listMeasurement.run {
            if (!data.strMeasure1.equals("") && !data.strMeasure1.equals(" ")) data.strMeasure1?.let { add(it) }
            if (!data.strMeasure2.equals("") && !data.strMeasure2.equals(" ")) data.strMeasure2?.let { add(it) }
            if (!data.strMeasure3.equals("") && !data.strMeasure3.equals(" ")) data.strMeasure3?.let { add(it) }
            if (!data.strMeasure4.equals("") && !data.strMeasure4.equals(" ")) data.strMeasure4?.let { add(it) }
            if (!data.strMeasure5.equals("") && !data.strMeasure5.equals(" ")) data.strMeasure5?.let { add(it) }
            if (!data.strMeasure6.equals("") && !data.strMeasure6.equals(" ")) data.strMeasure6?.let { add(it) }
            if (!data.strMeasure7.equals("") && !data.strMeasure7.equals(" ")) data.strMeasure7?.let { add(it) }
            if (!data.strMeasure8.equals("") && !data.strMeasure8.equals(" ")) data.strMeasure8?.let { add(it) }
            if (!data.strMeasure9.equals("") && !data.strMeasure9.equals(" ")) data.strMeasure9?.let { add(it) }
            if (!data.strMeasure10.equals("") && !data.strMeasure10.equals(" ")) data.strMeasure10?.let { add(it) }
            if (!data.strMeasure11.equals("") && !data.strMeasure11.equals(" ")) data.strMeasure11?.let { add(it) }
            if (!data.strMeasure12.equals("") && !data.strMeasure12.equals(" ")) data.strMeasure12?.let { add(it) }
            if (!data.strMeasure13.equals("") && !data.strMeasure13.equals(" ")) data.strMeasure13?.let { add(it) }
            if (!data.strMeasure14.equals("") && !data.strMeasure14.equals(" ")) data.strMeasure14?.let { add(it) }
            if (!data.strMeasure15.equals("") && !data.strMeasure15.equals(" ")) data.strMeasure15?.let { add(it) }
        }

        view()?.onShowIngredientData(listIngredient, listMeasurement)


    }
}