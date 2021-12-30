package com.nurtdinov.todocompose.navigation

import androidx.navigation.NavController
import com.nurtdinov.todocompose.util.Action
import com.nurtdinov.todocompose.util.Constants.LIST_SCREEN

class Screens(navController: NavController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}