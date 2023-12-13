package com.flexath.newsify.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var article = mutableStateOf<Article?>(null)
        private set

    val news = newsUseCases.getNewsUseCases(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

    fun getArticle(url: String) {
        viewModelScope.launch {
            article.value = newsUseCases.getArticle(url)
        }
    }

}