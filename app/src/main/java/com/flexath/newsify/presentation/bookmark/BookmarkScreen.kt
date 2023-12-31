package com.flexath.newsify.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.flexath.newsify.R
import com.flexath.newsify.domain.model.Article
import com.flexath.newsify.presentation.Dimens.MediumPadding1
import com.flexath.newsify.presentation.Dimens.SmallPadding1
import com.flexath.newsify.presentation.common.ArticleCardList
import com.flexath.newsify.presentation.navgraph.Route

@Composable
fun BookmarkScreen(
    bookmarkState: BookmarkState,
    navigate: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = SmallPadding1, end = SmallPadding1)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        ArticleCardList(
            articles = bookmarkState.articles.reversed(),
            onClick = {
                navigate(it)
            }
        )
    }
}