package com.nurtdinov.todocompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurtdinov.todocompose.data.Repository
import com.nurtdinov.todocompose.data.models.ToDoTask
import com.nurtdinov.todocompose.util.SearchAppBarState
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ViewModelScoped
class SharedViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks:StateFlow<List<ToDoTask>> = _allTasks

    fun getAllTask(){
        viewModelScope.launch {
            repository.getAllTask.collect{
                _allTasks.value = it
            }
        }
    }
}