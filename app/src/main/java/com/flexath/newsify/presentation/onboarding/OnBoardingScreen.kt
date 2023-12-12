package com.flexath.newsify.presentation.onboarding

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flexath.newsify.presentation.Dimens.MediumPadding1
import com.flexath.newsify.presentation.common.NewsButton
import com.flexath.newsify.presentation.common.NewsTextButton
import com.flexath.newsify.presentation.onboarding.components.OnBoardingPage
import com.flexath.newsify.presentation.onboarding.components.PageIndicator
import com.flexath.newsify.presentation.onboarding.events.OnBoardingEvent
import com.flexath.newsify.ui.theme.NewsifyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onEvent: (OnBoardingEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pageList.size
        }

        var buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf()
                }
            }
        }

        val context = LocalContext.current

        HorizontalPager(state = pagerState) { page ->
            OnBoardingPage(
                page = pageList[page]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding1)
                .navigationBarsPadding()
        ) {
            PageIndicator(
                modifier = Modifier.width(52.dp),
                pageSize = pageList.size,
                selectedPage = pagerState.currentPage
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val coroutineScope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0]
                    ) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(
                                page = pagerState.currentPage - 1
                            )
                        }
                    }
                }

                NewsButton(
                    text = buttonState.value[1]
                ) {
                    coroutineScope.launch {
                        if (pagerState.currentPage == 2) {
                            Toast.makeText(context, "Go to home screen", Toast.LENGTH_SHORT).show()
                            onEvent(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.scrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun OnBoardingScreenPreview() {
    NewsifyTheme {
        OnBoardingScreen {

        }
    }
}