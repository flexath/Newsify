package com.flexath.newsify.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.presentation.Dimens.SmallPadding1
import com.flexath.newsify.presentation.common.ArticleCardList
import com.flexath.newsify.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigate:(Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = SmallPadding1, start = SmallPadding1, end = SmallPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()

            ArticleCardList(
                articles = articles,
                onClick = navigate
            )
        }
    }
}