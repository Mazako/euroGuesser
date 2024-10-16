package pl.mazak.euroguesser.ui.results;

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ResultsViewModel(savedStateHandle: SavedStateHandle) : ViewModel(){
    val score = checkNotNull(savedStateHandle[ResultsPanelRoute.scoreArg])

}
