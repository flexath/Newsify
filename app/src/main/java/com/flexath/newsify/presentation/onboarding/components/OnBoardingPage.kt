package com.flexath.newsify.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.flexath.newsify.R
import com.flexath.newsify.presentation.Dimens.MediumPadding1
import com.flexath.newsify.presentation.Dimens.MediumPadding2
import com.flexath.newsify.presentation.Dimens.SmallPadding1
import com.flexath.newsify.presentation.Dimens.SmallPadding2
import com.flexath.newsify.presentation.onboarding.Page
import com.flexath.newsify.presentation.onboarding.pageList
import com.flexath.newsify.ui.theme.NewsifyTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = page.res),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        Text(
            text = page.title,
            modifier = Modifier.padding(top = MediumPadding1 , start = MediumPadding1, end = MediumPadding1),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )

        Text(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingPagePreview() {
    NewsifyTheme {
        OnBoardingPage(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            page = pageList[0]
        )
    }
}