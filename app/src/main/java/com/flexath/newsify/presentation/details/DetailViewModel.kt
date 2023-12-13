package com.flexath.newsify.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.domain.usecases.news.NewsUseCases
import com.flexath.newsify.presentation.details.events.DetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.SaveArticle -> {
                viewModelScope.launch {
                    insertArticle(article = event.article)
                }
            }

            is DetailEvent.DeleteArticle -> {
                viewModelScope.launch {
                    deleteArticle(article = event.article)
                }
            }

            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        article.isSaved = false
        newsUseCases.deleteArticle.invoke(article = article)
        sideEffect = "Article deleted"

    }

    private suspend fun insertArticle(article: Article) {
        article.isSaved = true
        newsUseCases.insertArticle.invoke(article = article)
        sideEffect = "Article saved"
    }
}