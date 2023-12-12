package com.flexath.newsify.presentation.details.events

sealed class DetailEvent {
    data object SaveArticle : DetailEvent()
}