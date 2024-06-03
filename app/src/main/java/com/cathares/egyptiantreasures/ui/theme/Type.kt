package com.cathares.egyptiantreasures.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cathares.egyptiantreasures.R


val mullerFamily = FontFamily(
    Font(R.font.muller_bold, FontWeight.Bold),
    Font(R.font.muller_extra_bold, FontWeight.ExtraBold),
    Font(R.font.muller_medium, FontWeight.Medium),
    Font(R.font.muller_regular, FontWeight.Normal),
)
val bodyMedium = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 21.3.sp
)
val titleBold = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp
)
val textRegular = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 21.3.sp
)
val numbersBold = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 21.sp,
    color = White
)
val spinBold = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 36.sp,
    color = White,
)
val spinBoldStroke = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 36.sp,
    color = Color(0xFFB38C00),
    drawStyle = Stroke(
        miter = 10f,
        width = 3.5f,
        join = StrokeJoin.Miter
    )
)
val textBold = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 20.sp,
    color = Color(0xFFFFFCBE),
)
val textBoldStroke = textBold.copy(
    color = Color(0xFFB38C00),
    drawStyle = Stroke(
        miter = 5f,
        width = 3f,
        join = StrokeJoin.Bevel
    )
)
val plusBold = TextStyle(
    fontFamily = mullerFamily,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 25.sp,
    color = Color(0xFFFFFCBE),
)
val plusBoldStroke = textBold.copy(
    color = Color(0xFFB38C00),
    drawStyle = Stroke(
        miter = 5f,
        width = 3f,
        join = StrokeJoin.Bevel
    )
)
val settingsBold = titleBold.copy(
    color = Color(0xFF000000)
)
val settingsMedium = bodyMedium.copy(
    fontSize = 25.sp,
    color = Color(0xFF000000)
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = mullerFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 27.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
