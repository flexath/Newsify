package com.flexath.newsify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.flexath.newsify.domain.usecases.AppEntryUseCases
import com.flexath.newsify.presentation.MainViewModel
import com.flexath.newsify.presentation.navgraph.NavGraph
import com.flexath.newsify.presentation.onboarding.OnBoardingScreen
import com.flexath.newsify.presentation.onboarding.OnBoardingViewModel
import com.flexath.newsify.ui.theme.NewsifyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashScreenCondition
            }
        }

        setContent {
            NewsifyTheme(
                dynamicColor = false
            ) {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsifyTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            OnBoardingScreen {

            }
        }
    }
}