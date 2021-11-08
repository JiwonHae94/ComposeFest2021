package com.jiwon.jetpackcomposelayouts.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiwon.jetpackcomposelayouts.model.Photographer
import com.jiwon.jetpackcomposelayouts.ui.theme.JetpackComposeLayoutsTheme


private val TAG = "PhotographerProfile"

@Composable
fun PhotographerProfile(
    photographer : Photographer
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                Log.d(TAG, "TODO something when clicked ")
            }
            .background(MaterialTheme.colors.surface)
            .clip(RoundedCornerShape(4.dp))
            .padding(16.dp)
    ){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ){

        }


        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ){
            Text(photographer.name, fontWeight = FontWeight.Bold)
            
            // LocalContentALpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(photographer.lastSeenOnline, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerProfile_Preview(){
    JetpackComposeLayoutsTheme {
        PhotographerProfile(photographer = Photographer(
            name = "Alfred Sisley",
            lastSeenOnline = "3 minutes ago"

        ))
    }
}
