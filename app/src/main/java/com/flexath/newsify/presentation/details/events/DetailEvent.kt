package com.flexath.newsify.presentation.details.events

import com.flexath.newsify.domain.model.Article

sealed class DetailEvent {
    data class SaveArticle(val article: Article) : DetailEvent()
    data object RemoveSideEffect : DetailEvent()
}