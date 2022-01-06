package com.nurtdinov.todocompose.navigation.destenations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nurtdinov.todocompose.ui.screens.splash.SplashScreen
import com.nurtdinov.todocompose.util.Constants.SPLASH_SCREEN

@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}