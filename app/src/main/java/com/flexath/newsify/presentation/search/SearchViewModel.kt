package com.flexath.newsify.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.flexath.newsify.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newUseCases: NewsUseCases
) : ViewModel() {

    var searchState = mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchEvent) {
        when {
            event is SearchEvent.UpdateSearchQuery -> {
                searchState.value = searchState.value.copy(
                    searchQuery = event.searchQuery
                )
            }
            event is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newUseCases.searchNews(
            searchQuery = searchState.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        searchState.value = searchState.value.copy(
            articles = articles
        )
    }
}