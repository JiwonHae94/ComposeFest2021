package com.jiwon.layoutscodelab.view

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Slot APIs
@Composable
fun SlotButton(
    modifier : Modifier = Modifier,
    text : String,
    spacingBetweenIconAndText : Dp = 4.dp,
    icon : Icon?,
    onClick : (() -> Unit)? = null,
    content : @Composable () -> Unit
){

}


@Preview
@Composable
fun Button_Preview(){

    SlotButton(
        modifier = Modifier,
        text = "Sample",
        icon = null
    ){
        Row{
            // TODO Something
        }
    }

}
