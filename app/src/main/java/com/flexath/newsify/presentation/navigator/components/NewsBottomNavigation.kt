package com.flexath.newsify.presentation.navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flexath.newsify.R
import com.flexath.newsify.presentation.navgraph.Route
import com.flexath.newsify.ui.theme.NewsifyTheme

@Composable
fun NewsBottomNavigation(
    navController: NavController
) {
    val backStackEntry = navController.currentBackStackEntryAsState().value

    var selectedBottomItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedBottomItemIndex = when (backStackEntry?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        navigationItemList.forEachIndexed { index, newsBottomNavigationItem ->
            NavigationBarItem(
                selected = selectedBottomItemIndex == index,
                onClick = {
                    selectedBottomItemIndex = index
                    navController.navigate(newsBottomNavigationItem.route) {
                        navController.graph.startDestinationRoute?.let { startDestinationRoute ->
                            popUpTo(startDestinationRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = {
                    Text(text = newsBottomNavigationItem.title)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = newsBottomNavigationItem.icon),
                        contentDescription = newsBottomNavigationItem.title
                    )
                }
            )
        }
    }
}

data class NewsBottomNavigationItem(
    val title: String,
    val route: String,
    @DrawableRes val icon: Int
)

private val navigationItemList = listOf(
    NewsBottomNavigationItem(
        title = "Home",
        route = Route.HomeScreen.route,
        icon = R.drawable.ic_home
    ),
    NewsBottomNavigationItem(
        title = "Search",
        route = Route.SearchScreen.route,
        icon = R.drawable.ic_search
    ),
    NewsBottomNavigationItem(
        title = "Bookmark",
        route = Route.BookmarkScreen.route,
        icon = R.drawable.ic_bookmark
    )
)

@Preview
@Composable
fun NewsBottomNavigationPreview() {
    NewsifyTheme {

    }
}