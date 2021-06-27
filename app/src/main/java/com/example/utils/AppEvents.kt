package com.example.utils

sealed class AppEvents {
    object NAVIGATION_EVENT : AppEvents()

    object TOGGLE_THEME_EVENT : AppEvents()
}
