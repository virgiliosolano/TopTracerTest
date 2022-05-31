package com.toptracer.virgiliomagalhaes.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toptracer.virgiliomagalhaes.repository.api.GiphyApiService
import com.toptracer.virgiliomagalhaes.repository.giphy.GiphyRepository

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(
                giphyRepository = GiphyRepository(
                    giphyApiService = GiphyApiService.createGiphyApiService()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}