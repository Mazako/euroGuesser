package pl.mazak.euroguesser.ui.mainMenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pl.mazak.euroguesser.data.countries

enum class SelectedMode {
    WRITE, SELECT
}

class MainMenuViewModel: ViewModel() {

    var selectedMode: SelectedMode by mutableStateOf(SelectedMode.SELECT)
        private set

    var count: String by mutableStateOf("10")
        private set

    fun updateSelectedMode(value: SelectedMode) {
        selectedMode = value
    }

    fun updateCount(value: String) {
        count = value
    }

    fun isCountValid(): Pair<Boolean, String?> {
        try {
            if (count.toInt() > countries.size) {
                return Pair(false, "Too much countries")
            }
            if (count.toInt() <= 0) {
                return Pair(false, "Too little countries")
            }
            return Pair(true, null)
        } catch (e: NumberFormatException) {
            return Pair(false, "Invalid input")
        }

    }


}