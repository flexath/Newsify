package com.flexath.newsify.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.flexath.newsify.presentation.onboarding.OnBoardingScreen
import com.flexath.newsify.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNavigation.route            // sub-graph
        ) {
            composable(
                route = Route.OnBoardingScreen.route,
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen {
                    viewModel.onAppEntryEvent(it)
                }
            }
        }

        navigation(
            startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route            // sub-graph
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                Text(text = "News navigator navigation")
            }
        }
    }
}