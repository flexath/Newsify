package com.flexath.newsify.presentation.onboarding

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}