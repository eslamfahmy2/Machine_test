package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.utils.AppEvents


@Composable
fun AppBar(
    onClick: (AppEvents) -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
    ) {

        val (darkMode, title, nav) = createRefs()

        IconButton(onClick = { onClick(AppEvents.TOGGLE_THEME_EVENT) },
            modifier = Modifier.constrainAs(darkMode) {
                start.linkTo(parent.start)
                end.linkTo(title.start)
            }) {
            Icon(Icons.Rounded.DarkMode, contentDescription = null)
        }


        Text(
            text = "NY Times most popular",
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.constrainAs(title) {
                centerTo(parent)
                start.linkTo(darkMode.end)
                end.linkTo(nav.start)
            }
        )


        IconButton(onClick = { onClick(AppEvents.NAVIGATION_EVENT) },
            modifier = Modifier.constrainAs(nav) {
                start.linkTo(title.end)
                end.linkTo(parent.end)
            }) {
            Icon(Icons.Rounded.ViewList, contentDescription = null)
        }

        createHorizontalChain(darkMode, title, nav, chainStyle = ChainStyle.SpreadInside)
    }


}