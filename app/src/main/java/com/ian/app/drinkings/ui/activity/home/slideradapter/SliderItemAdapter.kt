package com.ian.app.drinkings.ui.activity.home.slideradapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.drinkings.R
import com.ian.app.drinkings.data.model.Drinks
import com.ian.app.drinkings.helper.BeverageConstant.intentKeyToDetail
import com.ian.app.drinkings.ui.activity.detail.DetailDrinkActivity
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.startActivity
import kotlinx.android.synthetic.main.item_slider.view.*

/**
 *
Created by Ian Damping on 16/04/2019.
Github = https://github.com/iandamping
 */
class SliderItemAdapter(private val data: List<Drinks>, private val ctx: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val views = container.inflates(R.layout.item_slider)
        views.ivSliderImage.loadWithGlide(data[position].strDrinkThumb, ctx)
        views.ivSliderImage?.setOnClickListener {
            ctx.startActivity<DetailDrinkActivity> {
                putExtra(intentKeyToDetail, this@SliderItemAdapter.data[position].idDrink)

            }
        }
        container.addView(views)
        return views
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = data.size
}