package com.nurtdinov.todocompose.ui.screens.list


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nurtdinov.todocompose.R
import com.nurtdinov.todocompose.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.nurtdinov.todocompose.components.PriorityItem
import com.nurtdinov.todocompose.ui.theme.*
import com.nurtdinov.todocompose.ui.viewmodels.SharedViewModel
import com.nurtdinov.todocompose.util.SearchAppBarState
import com.nurtdinov.todocompose.util.TrailingIconState


@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED
                },
                onSortClick = {},

                onDeleteClicked = {}
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,

                onTextChange = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },

                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },

                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                }
            )
        }

    }


}

/** App Bar **/

@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClick = onSortClick,
                onDeleteClicked = onDeleteClicked
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor

    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClick: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchActions(onSearchClicked = onSearchClicked)
    SortAction(onSortClick = onSortClick)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchActions(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(onSortClick: (Priority) -> Unit) {

    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(onClick = {
                expended = false
                onSortClick(Priority.LOW)
            }) {
                PriorityItem(priority = Priority.LOW)

            }
            DropdownMenuItem(onClick = {
                expended = false
                onSortClick(Priority.HIGH)
            }) {
                PriorityItem(priority = Priority.HIGH)

            }
            DropdownMenuItem(onClick = {
                expended = false
                onSortClick(Priority.NONE)
            }) {
                PriorityItem(priority = Priority.NONE)

            }
        }
    }
}

@Composable
fun DeleteAllAction(onDeleteClicked: () -> Unit) {
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = { expended = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(onClick = {
                expended = false
                onDeleteClicked()
            }
            ) {
                Text(
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    text = stringResource(id = R.string.delete_all_action),
                    style = Typography.subtitle2
                )
            }

        }
    }
}

/** Search App Bar **/

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.search_place_holder_text),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    when (trailingIconState) {
                        TrailingIconState.READY_TO_DELETE -> {
                            onTextChange("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }
                        TrailingIconState.READY_TO_CLOSE -> {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                                trailingIconState = TrailingIconState.READY_TO_DELETE
                            }
                        }
                    }
                }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.close_icon),
                        tint = MaterialTheme.colors.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )

        )
    }
}


@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClick = {},
        onDeleteClicked = {}
    )

}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}