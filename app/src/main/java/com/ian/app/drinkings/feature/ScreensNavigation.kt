package com.ian.app.drinkings.feature

import com.ian.app.drinkings.feature.ScreensNavigation.ScreensNavigationConstant.DETAIL_SCREEN
import com.ian.app.drinkings.feature.ScreensNavigation.ScreensNavigationConstant.HOME_SCREEN

sealed class ScreensNavigation {

    data class LoadHome(val name: String = HOME_SCREEN) : ScreensNavigation()
    data class LoadDetail(val name: String = DETAIL_SCREEN) : ScreensNavigation()

    private object ScreensNavigationConstant {
        const val HOME_SCREEN = "Home Screen"
        const val DETAIL_SCREEN = "Detail Screen"
    }
}

enum class ScreensNavigationArgument {
    DetailScreen
}
