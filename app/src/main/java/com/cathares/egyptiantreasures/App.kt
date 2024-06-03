package com.cathares.egyptiantreasures

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cathares.egyptiantreasures.ui.CombinationsScreen
import com.cathares.egyptiantreasures.ui.FaqScreen
import com.cathares.egyptiantreasures.ui.GameScreen
import com.cathares.egyptiantreasures.ui.GameViewModel
import com.cathares.egyptiantreasures.ui.MainScreen
import com.cathares.egyptiantreasures.ui.PrivacyPolicyWebview
import com.cathares.egyptiantreasures.ui.SettingsScreen
import com.cathares.egyptiantreasures.ui.theme.titleBold


enum class AppScreen(@StringRes val title: Int, @DrawableRes val imageRes: Int ) {
    Main(title = R.string.app_name, imageRes = R.drawable.ic_settings_button),
    Settings(title = R.string.settings, imageRes = R.drawable.ic_back_button),
    Combinations(title = R.string.combinations, imageRes = R.drawable.ic_back_button),
    FAQ(title = R.string.faq, imageRes = R.drawable.ic_back_button),
    Game(title = R.string.game, imageRes = R.drawable.ic_back_button),
    PrivacyPolicy(title = R.string.privacy, imageRes = R.drawable.ic_back_button)
}

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    viewModel: GameViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Main.name
    )
    NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name
        ) {
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onPlayButtonClick = {navController.navigate(AppScreen.Game.name)},
                    onCombinationsButtonClick = {navController.navigate(AppScreen.Combinations.name)},
                    onFaqButtonClick = {navController.navigate(AppScreen.FAQ.name)},
                    onPrivacyButtonClick = {navController.navigate(AppScreen.PrivacyPolicy.name)},
                    context = LocalContext.current
                )
                TopBar(
                    currentScreen = currentScreen,
                    onClick = {navController.navigate(AppScreen.Settings.name)}
                )
            }
            composable(route = AppScreen.Settings.name) {
                SettingsScreen()
                TopBar(
                    currentScreen = currentScreen,
                    onClick = {navController.navigateUp()}
                )
            }
            composable(route = AppScreen.Combinations.name) {
                CombinationsScreen()
                TopBar(
                    currentScreen = currentScreen,
                    onClick = {navController.navigateUp()}
                )
            }
            composable(route = AppScreen.FAQ.name) {
                FaqScreen()
                TopBar(
                    currentScreen = currentScreen,
                    onClick = {navController.navigateUp()}
                )
            }
            composable(route = AppScreen.Game.name) {
                GameScreen(context = LocalContext.current, viewModel = viewModel, navigateBack = {navController.navigateUp()})
            }
            composable(route = AppScreen.PrivacyPolicy.name) {
                PrivacyPolicyWebview()
            }
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
fun TopBar(
    modifier: Modifier = Modifier,
    currentScreen: AppScreen,
    onClick: () -> Unit
) {
    val padding = Pair(32.dp, 55.dp)
    Row (modifier = Modifier
        .padding(25.dp, 42.dp)
        .fillMaxSize(),
    ) {
        DefaultButton(currentScreen.imageRes, modifier.padding(padding.first, padding.second)) {
            onClick()
        }
    }
    Row (modifier = Modifier
        .padding(32.dp, 37.dp)
        .fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(currentScreen.title),
            style = titleBold,
            color = Color.White
        )
    }
}
