package com.cathares.egyptiantreasures.ui

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cathares.egyptiantreasures.R
import com.cathares.egyptiantreasures.ui.theme.DarkYellow
import com.cathares.egyptiantreasures.ui.theme.numbersBold
import com.cathares.egyptiantreasures.ui.theme.settingsBold
import com.cathares.egyptiantreasures.ui.theme.settingsMedium
import com.cathares.egyptiantreasures.ui.theme.spinBold
import com.cathares.egyptiantreasures.ui.theme.spinBoldStroke
import com.cathares.egyptiantreasures.ui.theme.textBold
import com.cathares.egyptiantreasures.ui.theme.textBoldStroke
import com.primex.core.ExperimentalToolkitApi
import com.primex.core.blur.legacyBackgroundBlur
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    context: Context,
    viewModel: GameViewModel,
    navigateBack: () -> Unit
) {
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    val uiState by viewModel.uiState.collectAsState()
    val listOfStates = List(5) { rememberScrollState() }
    val gameField = uiState.field
    val isSettingsPressed =  rememberSaveable {mutableStateOf(false)}
    Box(modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        GameTopBar({navigateBack()}, isSettingsPressed)
        SpinButton(
            listOfStates = listOfStates,
            { viewModel.initSpin() },
            {viewModel.updateField()},
            uiState.balance,
            uiState.betSize
        )
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .padding(15.dp, 60.dp, 0.dp, 60.dp)
                    .requiredSize(420.dp, 260.dp)
                )
                {
                    Image(painter = painterResource(
                        id = R.drawable.spinner_block),
                        modifier = Modifier,
                        contentDescription = null)
                    Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.BottomStart) {
                        Bet({viewModel.updateBet(1000)}, {viewModel.updateBet(-1000)}, uiState.betSize)
                    }
                    Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.BottomEnd) {
                        Balance(uiState.balance)
                    }
                    Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.TopCenter) {
                        TotalWin(uiState.winAmount)
                    }
                    Row(
                        Modifier
                            .matchParentSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Column(
                            Modifier
                                .size(80.dp, 240.dp)
                                .verticalScroll(listOfStates[0])
                        ) {
                            for (element in gameField){
                                Slot(element[0])
                            }
                        }
                        Column(
                            Modifier
                                .size(80.dp, 240.dp)
                                .verticalScroll(listOfStates[1])
                        ) {
                            for (element in gameField){
                                Slot(element[1])
                            }
                        }
                        Column(
                            Modifier
                                .size(80.dp, 240.dp)
                                .verticalScroll(listOfStates[2])
                        ) {
                            for (element in gameField){
                                Slot(element[2])
                            }
                        }
                        Column(
                            Modifier
                                .size(80.dp, 240.dp)
                                .verticalScroll(listOfStates[3])
                        ) {
                            for (element in gameField){
                                Slot(element[3])
                            }
                        }
                        Column(
                            Modifier
                                .size(80.dp, 240.dp)
                                .verticalScroll(listOfStates[4])
                        ) {
                            for (element in gameField){
                                Slot(element[4])
                            }
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(enabled = false, state = rememberScrollState()))
                }
            }
        }
    }
    if (isSettingsPressed.value){
        Settings(state = isSettingsPressed)
    }
}



@Composable
fun SpinButton(
    listOfStates: List<ScrollState>,
    initSpin: () -> Unit,
    updateField: () -> Unit,
    balance: Int,
    betSize: Int
) {
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd ) {
        Button(
            shape = RoundedCornerShape(9.dp),
            enabled = (!listOfStates[0].isScrollInProgress && balance - betSize > 0),
            onClick = {
                coroutineScope.launch {
                    initSpin()
                    scroll(listOfStates)
                    updateField()
                    resetScroll(listOfStates)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkYellow,
                disabledContainerColor = DarkYellow
            ),
            modifier = Modifier
                .padding(30.dp)
                .shadow(
                    10.dp,
                    shape = RoundedCornerShape(10.dp),
                    ambientColor = Color(0xFFFFE600)
                )
                .widthIn(min = 190.dp)
        ) {
            Box(modifier = Modifier) {
                Text(
                    modifier = Modifier,
                    text = "Spin",
                    style = spinBold,
                )
                Text(
                    modifier = Modifier,
                    text = "Spin",
                    style = spinBoldStroke,
                )
            }
        }
    }
}
suspend fun scroll(listOfStates: List<ScrollState>) {
    coroutineScope {
        listOfStates.forEach { state ->
            launch {
                state.animateScrollTo(state.maxValue, animationSpec = tween(
                    durationMillis = 3000,
                    delayMillis = 50,
                    easing = LinearOutSlowInEasing
                )
                )
            }
        }
    }
}
suspend fun resetScroll(listOfStates: List<ScrollState>) {
    coroutineScope {
        listOfStates.forEach { state ->
            launch {
                state.scrollTo(0)
            }
        }
    }
}
@Composable
fun Bet(increment: () -> Unit, decrement: () -> Unit, betSize: Int){
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.offset(0.dp, 50.dp)) {
        Box(
            modifier = Modifier.offset(0.dp, -22.dp)) {
            Text(text = "Bet", style = textBold)
            Text(text = "Bet", style = textBoldStroke)
        }
        Row(
        ) {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_inc_back),
                    contentDescription = "decrement backplate",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { decrement() })
                Text(
                    text = "-",
                    style = textBold.copy(
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.offset(0.dp, -2.dp)
                )
                Text(
                    text = "-",
                    style = textBoldStroke.copy(
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.offset(0.dp, -2.dp)
                )
            }
            Box(modifier = Modifier.padding(4.dp, 0.dp), contentAlignment = Alignment.Center) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_balance),
                    contentDescription = "bet backplate",
                    modifier = Modifier.size(127.dp, 28.dp)
                )

                Text(
                    text = betSize.toString(),
                    style = numbersBold
                )
            }
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_inc_back),
                    contentDescription = "increment backplate",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { increment() })
                Text(
                    text = "+",
                    style = textBold.copy(
                        fontSize = 22.sp
                    )
                )
                Text(
                    text = "+",
                    style = textBoldStroke.copy(
                        fontSize = 22.sp
                    )
                )

            }
        }
    }
}

@Composable
fun Balance(balance: Int) {
    Box(modifier = Modifier
        .padding(3.dp, 0.dp)
        .offset(0.dp, 50.dp), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.offset(0.dp, -22.dp)) {
            Text(text = "Balance", style = textBold)
            Text(text = "Balance", style = textBoldStroke)
        }
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_balance),
            contentDescription = "balance backplate",
            modifier = Modifier.size(127.dp, 28.dp)
        )
        Text(
            text = balance.toString(),
            style = numbersBold
        )
    }
}

@Composable
fun TotalWin(winSize: Int) {
    Column(
        modifier = Modifier.offset(0.dp, -32.dp)
    ) {
        Box(modifier = Modifier.padding(0.dp, 0.dp), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.offset(0.dp, -22.dp)) {
                Text(text = "Win", style = textBold)
                Text(text = "Win", style = textBoldStroke)
            }
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_balance),
                contentDescription = "balance backplate",
                modifier = Modifier.size(127.dp, 28.dp)
            )
            Text(
                text = winSize.toString(),
                style = numbersBold
            )
        }
    }
}

@Composable
fun Slot(randNum: Int) {
    val nums = mapOf(
        Pair(0, R.drawable.ic_slot_1),
        Pair(1, R.drawable.ic_slot_2),
        Pair(2, R.drawable.ic_slot_3),
        Pair(3, R.drawable.ic_slot_4),
        Pair(4, R.drawable.ic_slot_5),
        )
    Image(
        bitmap = ImageBitmap.imageResource(id = nums[randNum]!!),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun GameTopBar(
    navigateBack: () -> Unit,
    isSettingsPressed: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .padding(10.dp, 1.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { navigateBack() },
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
            onClick = { isSettingsPressed.value = true },
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
@OptIn(ExperimentalToolkitApi::class)
@Composable
fun Settings(state: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .legacyBackgroundBlur(radius = 25f)
            .fillMaxSize()
            .padding(210.dp, 55.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color(0xFFFAF7B1))
            .border(width = 4.dp, color = Color(0xFFFFC700), shape = RoundedCornerShape(15.dp))
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
                Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings_back),
                    contentDescription = "back_arrow",
                    modifier = Modifier
                        .padding(20.dp, 22.dp)
                        .size(25.dp)
                        .clickable {
                            state.value = false
                        }
                )
                Text(
                    text = "Settings",
                    style = settingsBold,
                    modifier = Modifier.padding(65.dp, 20.dp)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Vibration", style = settingsMedium,modifier = Modifier.padding(0.dp, 10.dp))
                SettingsSwitch()
            }
            SettingsDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Sound", style = settingsMedium,modifier = Modifier.padding(0.dp, 10.dp))
                SettingsSwitch()
            }
            SettingsDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Music", style = settingsMedium,modifier = Modifier.padding(0.dp, 10.dp))
                SettingsSwitch()
            }
        }
    }
}
@Composable
fun SettingsSwitch() {
    Switch(
        checked = false,
        onCheckedChange = {},
        colors = SwitchDefaults.colors(
            uncheckedTrackColor = Color(0xFFD4BF00),
            checkedTrackColor = Color(0xFFFFE500),
            checkedThumbColor = Color(0xFFE7EBF0),
            uncheckedThumbColor = Color(0xFFE7EBF0),
            uncheckedBorderColor = Color(0xFFD4BF00)
        )
    )
}

@Composable
fun SettingsDivider() {
    Divider(thickness = 2.dp, modifier = Modifier.padding(20.dp, 5.dp), color = Color(0xFFCAC665))
}
