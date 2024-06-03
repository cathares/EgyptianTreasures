package com.cathares.egyptiantreasures.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cathares.egyptiantreasures.ui.theme.BackgroundBlue
import com.cathares.egyptiantreasures.ui.theme.bodyMedium

@Composable
fun SettingsScreen() {
    val defaultPadding = Pair(0.dp, 5.dp)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundBlue
    ) {
        Column(
            modifier = Modifier.padding(25.dp, 116.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Vibration",
                    modifier = Modifier.padding(0.dp, 10.dp),
                    color = Color.White,
                    style = bodyMedium
                )
                DefaultSwitch()
            }
            Divider(
                modifier = Modifier.padding(defaultPadding.first, defaultPadding.second),
                thickness = 1.dp,
                color = Color.White
            )
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Sound",
                    modifier = Modifier.padding(0.dp, 10.dp),
                    color = Color.White,
                    style = bodyMedium
                )
                DefaultSwitch()
            }
            Divider(
                modifier = Modifier.padding(defaultPadding.first, defaultPadding.second),
                thickness = 1.dp,
                color = Color.White
            )
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Music",
                    modifier = Modifier.padding(0.dp, 10.dp),
                    color = Color.White,
                    style = bodyMedium
                )
                DefaultSwitch()
            }
        }
    }
}
@Composable
fun DefaultSwitch() {
    Switch(
        checked = false,
        onCheckedChange = {},
        colors = SwitchDefaults.colors(
            uncheckedTrackColor = Color(0xFFA93838),
            checkedTrackColor = Color(0xFFFF5858),
            checkedThumbColor = Color(0xFFE7EBF0),
            uncheckedThumbColor = Color(0xFFE7EBF0),
            uncheckedBorderColor = Color(0xFFA93838)
        )
    )
}

@Composable
@Preview
fun myPreview() {
    SettingsScreen()
}
