package com.example.egyptiantreasures.ui

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.egyptiantreasures.R
import com.example.egyptiantreasures.ui.theme.BlueMain


@Composable
fun GameScreen(context: Context) {
    var activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        GameTopBar()
        Button(onClick = { /*TODO*/ }) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.spin),
                contentDescription = null,
            )
        }

    }
}


@Composable
fun GameTopBar() {
    Row(
        modifier = Modifier
            .padding(20.dp, 16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.wrapContentSize(),
        ) {
            Box(
                modifier = Modifier.size(36.dp),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "ButtonBackplate")
                Image(
                    modifier = Modifier
                        .size(15.dp)
                        .offset(0.dp, -3.dp),
                    painter = painterResource(id = R.drawable.ic_in_game_arrow),
                    contentDescription = "ButtonBackplate")
            }
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.wrapContentSize()
        ) {
            Box(
                modifier = Modifier.size(36.dp),
                contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "ButtonBackplate")
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(0.dp, (-3.5).dp),
                    painter = painterResource(id = R.drawable.ic_game_settings),
                    contentDescription = "ButtonBackplate")
            }
        }
    }
}

@Composable
fun WinField(){}

@Preview
@Composable
fun MyPreview() {
    GameScreen(context = LocalContext.current)
}