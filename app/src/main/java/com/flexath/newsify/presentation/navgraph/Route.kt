package com.flexath.newsify.presentation.navgraph

import com.flexath.newsify.util.Constants

sealed class Route(val route: String) {
    // Screens
    data object OnBoardingScreen: Route(Constants.ON_BOARDING_ROUTE)
    data object HomeScreen: Route(Constants.HOME_SCREEN)
    data object SearchScreen: Route(Constants.SEARCH_SCREEN)
    data object BookmarkScreen: Route(Constants.BOOKMARK_SCREEN)
    data object DetailScreen: Route(Constants.DETAIL_SCREEN)
    data object NewsNavigatorScreen: Route(Constants.NEWS_NAVIGATOR_SCREEN)

    // sub-graphs
    data object AppStartNavigation: Route(Constants.APP_START_NAVIGATION)
    data object NewsNavigation: Route(Constants.NEWS_NAVIGATION)
}