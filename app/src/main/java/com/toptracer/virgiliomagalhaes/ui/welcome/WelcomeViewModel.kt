package com.toptracer.virgiliomagalhaes.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.toptracer.virgiliomagalhaes.repository.giphy.GiphyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeViewModel(private val giphyRepository: GiphyRepository) : ViewModel() {

    private val _welcomeResult = MutableLiveData<WelcomeResultView>()
    val welcomeResult: LiveData<WelcomeResultView> = _welcomeResult

    init {
        loadGiphy()
    }

    private fun loadGiphy() {
        viewModelScope.launch {
            requestGiphy()
        }
    }

    private suspend fun requestGiphy() {
        withContext(Dispatchers.IO) {
            val giphyResult = giphyRepository.getTrendingGiphy()

            if (giphyResult.isSuccess) {
                _welcomeResult.postValue(
                    WelcomeResultView(
                        giphyResult.getOrNull()?.images?.original?.url,
                        giphyResult.getOrNull()?.title,
                        giphyResult.getOrNull()?.username
                    )
                )
            } else {
                _welcomeResult.postValue(
                    WelcomeResultView(error = giphyResult.exceptionOrNull()?.localizedMessage)
                )
            }
        }
    }
}