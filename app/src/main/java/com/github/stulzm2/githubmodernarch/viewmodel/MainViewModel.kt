package com.github.stulzm2.githubmodernarch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.stulzm2.githubmodernarch.model.Item

class MainViewModel : ViewModel() {

    private val movieRepository = RestApiRepository()
    val allRepos: LiveData<List<Item>> get() = movieRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        movieRepository.viewModelJob.cancel()
    }
}