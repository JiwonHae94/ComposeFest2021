package com.jiwon.layoutscodelab.view

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import com.jiwon.layoutscodelab.view.ui.theme.LayoutsCodelabTheme

@ExperimentalComposeUiApi
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button1, button2,  text) = createRefs()
        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)

            // Centers Text horizontally in the ConstraintLayout
            //centerHorizontallyTo(parent)

            centerAround(button1.end)
        })

        // barriers (and all the other helpers) can be created in the body of ConstraintLayout, but not inside constrainAs.
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* do something*/ },
            modifier = Modifier.constrainAs(button2){
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ){
            Text("Button 2")
        }
    }
}

@Composable
fun LargeConstraintLayout(){
    ConstraintLayout{
        val text = createRef()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very long text",
            Modifier.constrainAs(text){
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            }
        )
    }
}

@Composable
fun DecoupledConstraintLayout(){
    BoxWithConstraints {
        val constraint = if(maxWidth < maxHeight){
            decoupledConstraints(margin = 16.dp)
        }else{
            decoupledConstraints(margin = 32.dp)
        }

        ConstraintLayout(constraint){
            Button(
                onClick = { /* Do something */},
                modifier = Modifier.layoutId("button")
            ){
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin : Dp) : ConstraintSet {
    return ConstraintSet{
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top, margin = margin )
        }

        constrain(text){
            top.linkTo(button.bottom, margin)
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun ConstraintLayoutContentPreview(){
    LayoutsCodelabTheme {
        LargeConstraintLayout()
    }
}