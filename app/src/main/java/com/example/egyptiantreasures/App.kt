package com.example.egyptiantreasures

import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.egyptiantreasures.ui.theme.BlueMain
import com.example.egyptiantreasures.ui.theme.mullerFamily


enum class AppScreen(@StringRes val title: Int, @DrawableRes val imageRes: Int ) {
    Main(title = R.string.app_name, imageRes = R.drawable.ic_settings_button),
    Settings(title = R.string.settings, imageRes = R.drawable.ic_back_button),
    Combinations(title = R.string.combinations, imageRes = R.drawable.ic_back_button),
    FAQ(title = R.string.faq, imageRes = R.drawable.ic_back_button)
}

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BlueMain
    ) {
        TopBar(currentScreen = AppScreen.Combinations)
    }
}


@Composable
fun DefaultButton(
    @DrawableRes imageRes: Int,
    modifier: Modifier,
    onClick: () -> Unit

) {
    IconButton(onClick = onClick, modifier = Modifier.size(32.dp)) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .clipToBounds())
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, currentScreen: AppScreen) {
    val padding = Pair(32.dp, 55.dp)
    Row (modifier = Modifier
        .padding(25.dp, 58.dp)
        .fillMaxSize(),
    ) {
        DefaultButton(currentScreen.imageRes, modifier.padding(padding.first, padding.second), {})
    }
    Row (modifier = Modifier
        .padding(32.dp, 50.dp)
        .fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(currentScreen.title),
            fontFamily = mullerFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            color = Color.White
        )
    }
    SettingsScreen()
}

@Composable
fun SettingsScreen() {
    val defaultPadding = Pair(0.dp, 10.dp)
    Column(
        modifier = Modifier.padding(25.dp, 116.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.padding(0.dp, 10.dp),
                text = "Vibration",
                textAlign = TextAlign.Left,
                fontFamily = mullerFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 27.sp,
                color = Color.White
            )
            Switch(checked = false, onCheckedChange = {})
        }
        Row {
            Divider(
                thickness = 1.dp,
                color = Color.White
            )
        }

    }
}
@Preview
@Composable
fun MyPreview(){    
    App()
}