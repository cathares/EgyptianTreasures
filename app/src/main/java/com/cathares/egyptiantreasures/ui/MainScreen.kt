package com.cathares.egyptiantreasures.ui

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.cathares.egyptiantreasures.R
import com.cathares.egyptiantreasures.ui.theme.BackgroundBlue
import com.cathares.egyptiantreasures.ui.theme.ButtonBlue
import com.cathares.egyptiantreasures.ui.theme.ButtonRed
import com.cathares.egyptiantreasures.ui.theme.textRegular
import com.cathares.egyptiantreasures.ui.theme.titleBold

@Composable
fun MainScreen(
    onPlayButtonClick: () -> Unit,
    onCombinationsButtonClick: () -> Unit,
    onFaqButtonClick: () -> Unit,
    onPrivacyButtonClick: () -> Unit = {},
    context: Context
) {
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundBlue
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier
                .size(237.dp)
                .fillMaxWidth())
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.menu_picture),
                contentDescription = null,
                modifier = Modifier.padding(40.dp))
            Button(
                onClick = { onPlayButtonClick() },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(60.dp)
                    .padding(40.dp, 0.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonRed
                )
            ) {
                Text(
                    text = "Play",
                    style = titleBold,
                )
            }
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                MenuButton(
                    onClick = { onCombinationsButtonClick() },
                    modifier = Modifier
                        .widthIn(min = 207.dp),
                    text = "Combinations")
                MenuButton(
                    onClick = { onFaqButtonClick() },
                    modifier = Modifier
                        .widthIn(min = 100.dp)
                        .padding(16.dp),
                    text = "FAQ")
                MenuButton(
                    onClick = { onPrivacyButtonClick() },
                    modifier = Modifier.widthIn(178.dp),
                    text = "Privacy policy")
            }
        }
    }
}

@Composable
fun MenuButton(
    onClick: () -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(25.dp),
    modifier: Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
    text: String)
{
    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier.shadow(5.dp, shape = RoundedCornerShape(25.dp)),
        colors = colors) {
        Text(
            text = text,
            style = textRegular,
            modifier = Modifier
        )
    }
}