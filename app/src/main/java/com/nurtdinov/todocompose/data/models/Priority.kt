package com.nurtdinov.todocompose.data.models


import androidx.compose.ui.graphics.Color
import com.nurtdinov.todocompose.ui.theme.HighPriorityColor
import com.nurtdinov.todocompose.ui.theme.LowPriorityColor
import com.nurtdinov.todocompose.ui.theme.MediumPriorityColor
import com.nurtdinov.todocompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}