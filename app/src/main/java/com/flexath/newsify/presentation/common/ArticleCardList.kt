package com.flexath.newsify.presentation.common

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.presentation.Dimens.ExtraSmallPadding2
import com.flexath.newsify.presentation.Dimens.MediumPadding1
import com.flexath.newsify.presentation.Dimens.SmallPadding1

@Composable
fun ArticleCardList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(ExtraSmallPadding2)
    ) {
        items(count = articles.size) { index ->
            val article = articles[index]
            ArticleCard(article = article) {
                onClick(article)
            }
        }
    }
}

@Composable
fun ArticleCardList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) { index ->
                articles[index]?.let {
                    ArticleCard(
                        article = it
                    ) {
                        onClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(SmallPadding1)
            )
        }
    }
}

/*
    loadState.refresh is LoadState.Error: This checks if the refresh operation
    (typically triggered by a swipe-to-refresh gesture) has resulted in an error.

    loadState.prepend is LoadState.Error: This checks if there was an error
    while prepending data (loading data at the beginning).

    loadState.append is LoadState.Error: This checks if there was an error
    while appending data (loading more data at the end).
 */