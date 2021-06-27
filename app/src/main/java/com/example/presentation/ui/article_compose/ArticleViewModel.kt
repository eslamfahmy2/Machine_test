package com.example.presentation.ui.article_compose

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.model.ArticleModel
import com.example.repo.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repo: ArticleRepository
) : ViewModel() {

    private val TAG = "MainActivityViewModel"
    val list: MutableState<List<ArticleModel>> = mutableStateOf(listOf())
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val listRx get() = list.value

    fun onTriggerEvent(event: ArticleEvent) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "onTriggerEvent: "+ event)
                when (event) {
                    is ArticleEvent.INT_LIST_EVENT -> {
                        isLoading.value = true;
                        list.value = repo.listArticle()
                        isLoading.value = false
                    }
                    is ArticleEvent.REFRESH_EVENT -> {
                        isLoading.value = true;
                        list.value = repo.listArticle()
                        isLoading.value = false
                    }

                }
            } catch (e: Exception) {
                Log.d(TAG, "onTriggerEvent: " + e.message)
            }
        }

    }

    suspend fun listRxEvent() {
        isLoading.value = true;
        list.value = repo.listArticleRx().results;
        isLoading.value = false
    }


}