package com.github.stulzm2.githubmodernarch.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.stulzm2.githubmodernarch.model.Item
import com.github.stulzm2.githubmodernarch.networking.RestApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

class RestApiRepository {
    private var movies = mutableListOf<Item>()
    private var mutableLiveData = MutableLiveData<List<Item>>()
    val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val thisApiCorService by lazy {
        RestApiService.createCorService()
    }

    fun getMutableLiveData(): MutableLiveData<List<Item>> {
        uiScope.launch {
            val request = thisApiCorService.reposForUserAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.items != null) {
                        movies = response.items as MutableList<Item>
                        mutableLiveData.value = movies
                    }
                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //
                }
            }
        }
        return mutableLiveData
    }
}