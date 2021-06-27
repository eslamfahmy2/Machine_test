package com.example.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.machinetest.R
import com.example.network.model.ArticleModel
import com.example.utils.loadPicture


@Composable
fun MovieCard(
    article: ArticleModel
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                //color = Color.Yellow
                color = MaterialTheme.colors.surface
            ),
    ) {


        val (userImage, layoutInfo, des, publisher, date) = createRefs()


        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(0.3f)
                .constrainAs(userImage) {
                    centerVerticallyTo(parent)
                },
            contentAlignment = Alignment.Center


        ) {

            val image = loadPicture(
                url = article.getImage(),
                defaultImage = R.drawable.ic_launcher_background
            ).value

            image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .width(article.getWith().dp)
                        .height(article.getHeight().dp)
                        .clip(CircleShape),
                )

            }
        }

        Text(
            text = article.title,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.7f)
                .constrainAs(layoutInfo) {

                },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Left,
                fontSize = 16.sp,
            )

        )


        Text(
            text = article.byline,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 8.dp)
                .constrainAs(des) {
                    start.linkTo(userImage.end)
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Left,
                fontSize = 18.sp
            ),

            )


        Text(
            text = article.section,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(8.dp)
                .constrainAs(publisher) {
                    start.linkTo(userImage.end)
                },

            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Left,
                fontSize = 16.sp
            )
        )




        Text(
            text = article.published_date,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(8.dp)
                .constrainAs(date) {
                    top.linkTo(des.bottom)
                    start.linkTo(publisher.end)
                },
            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                fontSize = 16.sp,
            )
        )

        createHorizontalChain(userImage, layoutInfo)
        createVerticalChain(layoutInfo, des, publisher)
        //  createHorizontalChain(publisher, date)


    }


}