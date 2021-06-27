package com.example.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.network.model.ArticleModel

@Composable
fun BottomArticleDetails(
    article: ArticleModel
) {

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (txt1, txt2) = createRefs()

        Text(text = article.abstract, modifier = Modifier
            .padding(8.dp)
            .constrainAs(txt1) {})


        Text(text = article.desFacet.joinToString(), modifier = Modifier
            .padding(8.dp)
            .constrainAs(txt2) {})

        createVerticalChain(txt1, txt2)
    }

}