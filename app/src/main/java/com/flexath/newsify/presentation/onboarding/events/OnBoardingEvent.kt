package com.flexath.newsify.presentation.onboarding.events

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}