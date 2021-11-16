package com.jiwon.layoutscodelab.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jiwon.layoutscodelab.view.ui.theme.LayoutsCodelabTheme


//fun Modifier.customLayoutModifier() = Modifier.layout { measurable, constraints ->
//
//})

fun Modifier.firstBaselineToTop(
    firstBaselineToTop : Dp
) = this.then(layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY

    layout(placeable.width, height){
        placeable.placeRelative(0, placeableY)
    }
})

@Preview
@Composable
fun TextWithPaddingtoBaselinePreview(){
    LayoutsCodelabTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp), color = Color.White)
    }
}


@Preview
@Composable
fun TextWithNormalPaddingPreview(){
    LayoutsCodelabTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp), color = Color.White)
    }
}