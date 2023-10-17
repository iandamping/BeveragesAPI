package com.ian.app.drinkings.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ian.app.drinkings.feature.home.HomeScreen

@Composable
fun BeverageNavigationHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.LoadHome().name,
        builder = {
            composable(ScreensNavigation.LoadHome().name) {
                HomeScreen(
                    selectedBeverageId = { selectedFoodId ->
                        navController.navigate("${ScreensNavigation.LoadDetail().name}/$selectedFoodId")
                    }
                )
            }

//            composable(
//                "${ScreensNavigation.LoadDetail().name}/{${ScreensNavigationArgument.DetailFood.name}}",
//                arguments = listOf(navArgument(ScreensNavigationArgument.DetailFood.name) {
//                    type = NavType.IntType
//                })
//            ) {
//                DetailScreen(
//                    navigateUp = {
//                        navController.navigateUp()
//                    },
//                    selectFood = { selectedFoodId ->
//                        navController.navigate("${ScreensNavigation.LoadDetail().name}/$selectedFoodId")
//                    }
//
//                )
//            }
        }
    )
}
