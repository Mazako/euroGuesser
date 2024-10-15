package pl.mazak.euroguesser.ui

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import pl.mazak.euroguesser.ui.game.GamePanelViewModel
import pl.mazak.euroguesser.ui.mainMenu.MainMenuViewModel
import pl.mazak.euroguesser.ui.results.ResultsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainMenuViewModel()
        }

        initializer {
            GamePanelViewModel(this.createSavedStateHandle())
        }

        initializer {
            ResultsViewModel(this.createSavedStateHandle())
        }
    }
}