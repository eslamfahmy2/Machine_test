package com.example.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ExpandableCardComposable(
    isExpandable: Boolean = false,
    topContent: @Composable () -> Unit,
    buttomContent: @Composable () -> Unit,
    onExpandChange: (Boolean) -> Unit
) {
    val transactionState = remember {
        MutableTransitionState(isExpandable)
            .apply {
                targetState = isExpandable
            }
    }
    val transaction = updateTransition(transactionState, label = "")

    Card(
        modifier = Modifier.padding(12.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.small,
    ) {


        ConstraintLayout(modifier = Modifier.animateContentSize()) {

            val (top, bot) = createRefs()


            Row(
                modifier = Modifier
                    .clickable(onClick = {
                        transactionState.targetState = !transaction.currentState
                        onExpandChange(!transaction.targetState)
                    })
                    .constrainAs(top) {
                    },
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Box(modifier = Modifier.weight(0.8f)) {
                    topContent()
                }

                val iconId =
                    if (transaction.currentState) com.example.machinetest.R.drawable.ic_close else com.example.machinetest.R.drawable.ic_open
                Image(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(alignment = CenterVertically)

                )

            }

            if (transactionState.currentState) {
                Box(
                    modifier = Modifier
                        .clickable(onClick = {
                            transactionState.targetState = !transaction.currentState
                        })
                        .constrainAs(bot) {
                        }
                        .fillMaxWidth(),
                ) {
                    buttomContent()
                }
            }

            createVerticalChain(top, bot)
        }


    }
}