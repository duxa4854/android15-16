package com.ponomarenckozaichenko.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.ponomarenckozaichenko.ui_model.*

@Composable
fun StudentPlannerNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(
                onSubjectClick = { subjectId ->
                    navController.navigate(Screen.Details.createRoute(subjectId))
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("subjectId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString("subjectId")
            if (!subjectId.isNullOrEmpty()) {
                DetailsScreen(
                    subjectId = subjectId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }


        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}