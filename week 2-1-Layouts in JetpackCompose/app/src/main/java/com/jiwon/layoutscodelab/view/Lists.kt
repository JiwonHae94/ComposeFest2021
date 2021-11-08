package com.jiwon.layoutscodelab.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.jiwon.layoutscodelab.ui.theme.LayoutsCodelabTheme
import kotlinx.coroutines.launch

@Composable
fun SimpleList(){
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)){
        repeat(100){
            Text("Item #${it + 1}")
        }
    }
}

@Composable
fun SimpleLazyList(){

    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState){
        items(100){
            Text("Item #${it + 1}")
        }
    }
}

@Composable
fun ImageListItem(index : Int){
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList(state : LazyListState = rememberLazyListState()){
    LazyColumn(state = state){
        items(100){
            ImageListItem(index = it)
        }
    }
}


@Composable
fun ScrollingList(
    modifier : Modifier = Modifier
) {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button( onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            })  {
                Text("Scroll to the Top")
            }
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }){
                Text("Scroll to the End")
            }
        }

        ImageList(scrollState)
    }
}


@Preview(showBackground = true)
@Composable
fun SimplleList_Preview(){
    LayoutsCodelabTheme {
        ScrollingList()
    }
}