package com.flexath.newsify.domain.usecases

import com.flexath.newsify.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}