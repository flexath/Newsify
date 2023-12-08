package com.flexath.newsify.presentation.onboarding

import androidx.annotation.DrawableRes
import com.flexath.newsify.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val res: Int
)

val pageList = listOf(
    Page(
        title = "Welcome to Newsify",
        description = "Explore real-time updates, trending stories, and personalized content for an informed and inspiring news experience.",
        res = R.drawable.onboarding1
    ),
    Page(
        title = "Tailor Your Newsfeed",
        description = "Make Newsify truly yours! Customize your newsfeed by choosing topics that interest you the most. From breaking news to niche interests, pick and personalize your content. Your news, your way - because everyone's interests are unique.",
        res = R.drawable.onboarding2
    ),
    Page(
        title = " Discover the Power of Trends",
        description = "Dive into trends with Newsify, ensuring you never miss out on the stories that matter, engaging with diverse perspectives globally and locally.",
        res = R.drawable.onboarding3
    )
)
