package com.cathares.egyptiantreasures.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cathares.egyptiantreasures.App
import com.cathares.egyptiantreasures.R
import com.cathares.egyptiantreasures.ui.theme.BackgroundBlue
import com.cathares.egyptiantreasures.ui.theme.numbersBold


@Composable
fun CombinationsScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundBlue
    ) {
        Column(
            modifier = Modifier.padding(18.dp, 103.dp)
        ) {
            Row {
                Combination(number = 1, imageRes = R.drawable.ic_comb_1)
                Combination(number = 2, imageRes = R.drawable.ic_comb_2)
                Combination(number = 3, imageRes = R.drawable.ic_comb_3)
            }
            Row(
                modifier = Modifier.padding(0.dp,  19.dp).offset(x = (-4).dp)
            ){
                Combination(number = 4, imageRes = R.drawable.ic_comb_4)
                Combination(number = 5, imageRes = R.drawable.ic_comb_5)
                Combination(number = 6, imageRes = R.drawable.ic_comb_6, Modifier.offset((-2).dp))
            }
            Row(
                modifier = Modifier.offset(x = (-2).dp)
            ){
                Combination(number = 7, imageRes = R.drawable.ic_comb_7)
                Combination(number = 8, imageRes = R.drawable.ic_comb_8, Modifier.offset(x = (-2).dp))
                Combination(number = 9, imageRes = R.drawable.ic_comb_9, Modifier.offset(x = (-2).dp))
            }
            Row(
                modifier = Modifier.padding(0.dp, 19.dp).offset(x = (-15).dp)
            ){
                Combination(number = 10, imageRes = R.drawable.ic_comb_10)
            }
        }
    }
}

@Composable
fun Combination(number: Int, @DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    val defaultSize = Pair(72.6.dp, 40.6.dp)
    val padding = if (number%3 == 2 || number%3 == 0) { 20.dp } else { 0.dp }
    Text(
        text = "$number",
        style = numbersBold,
        modifier = modifier
            .padding(5.3.dp)
            .padding(padding, 0.dp, 0.dp, 0.dp)
    )
    Image(
        imageVector = ImageVector.vectorResource(imageRes),
        modifier = modifier.size(defaultSize.first, defaultSize.second),
        contentDescription = "1st combination")
}

@Preview
@Composable
fun MyPreview(){
    App()
}