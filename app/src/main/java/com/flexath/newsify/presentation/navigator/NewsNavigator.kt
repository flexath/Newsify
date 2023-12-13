package com.flexath.newsify.presentation.navigator

import android.media.metrics.Event
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.presentation.bookmark.BookmarkScreen
import com.flexath.newsify.presentation.bookmark.BookmarkViewModel
import com.flexath.newsify.presentation.details.DetailScreen
import com.flexath.newsify.presentation.details.DetailViewModel
import com.flexath.newsify.presentation.details.events.DetailEvent
import com.flexath.newsify.presentation.home.HomeScreen
import com.flexath.newsify.presentation.home.HomeViewModel
import com.flexath.newsify.presentation.navgraph.Route
import com.flexath.newsify.presentation.navigator.components.NewsBottomNavigation
import com.flexath.newsify.presentation.search.SearchScreen
import com.flexath.newsify.presentation.search.SearchViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun NewsNavigator() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value

    val bottomBarVisibility = remember(key1 = backStackEntry) {
        backStackEntry?.destination?.route == Route.HomeScreen.route ||
                backStackEntry?.destination?.route == Route.SearchScreen.route ||
                backStackEntry?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(bottomBarVisibility) {
                NewsBottomNavigation(navController = navController)
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles) { article ->
                    viewModel.getArticle(article.url)
                    article.isSaved = viewModel.article.value != null

                    article.let {
                        navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    }
                    navController.navigate(Route.DetailScreen.route)
                }
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.searchState.value

//                OnBackClickStateSaver(navController = navController)

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigate = { article ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                        navController.navigate(Route.DetailScreen.route)
                    }
                )
            }

            composable(
                route = Route.DetailScreen.route
            ) {
                val viewModel: DetailViewModel = hiltViewModel()

                viewModel.sideEffect?.let {
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }

//                OnBackClickStateSaver(navController = navController)

                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        DetailScreen(
                            article = article,
                            onEvent = viewModel::onEvent
                        ) {
                            navController.navigateUp()
                            navController.popBackStack()
                        }
                    }
            }

            composable(
                route = Route.BookmarkScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    bookmarkState = state,
                ) { article ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    navController.navigate(Route.DetailScreen.route)
                }
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navController.navigate(Route.HomeScreen.route)
    }
}