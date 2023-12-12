package com.flexath.newsify.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexath.newsify.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew(article: Article)

    @Delete
    fun deleteNew(article: Article)

    @Query("SELECT * FROM Article")
    fun getNews() : Flow<List<Article>>
}