package com.example.presentation.ui.article_compose

sealed class ArticleEvent {
    object INT_LIST_EVENT : ArticleEvent()
    object RX_LIST_EVENT : ArticleEvent()
    object REFRESH_EVENT : ArticleEvent()
}
