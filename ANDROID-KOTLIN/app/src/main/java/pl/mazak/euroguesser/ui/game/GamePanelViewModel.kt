package pl.mazak.euroguesser.ui.game

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mazak.euroguesser.data.Country
import pl.mazak.euroguesser.data.countries
import pl.mazak.euroguesser.data.pickThreeRandomCountries
import pl.mazak.euroguesser.ui.mainMenu.SelectedMode

class GamePanelViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val inputMode: String = checkNotNull(savedStateHandle[GamePanelRoute.inputModeArg])
    private val count: Int = checkNotNull(savedStateHandle[GamePanelRoute.countriesCountArg])
    private var countriesToGuess: ArrayDeque<Country> = ArrayDeque()

    private val _uiState = MutableStateFlow(GamePanelUiState())

    var uiState = _uiState.asStateFlow()
        private set

    init {
        val isSelectMode = inputMode == SelectedMode.SELECT.toString()

        countriesToGuess = ArrayDeque(countries.shuffled().subList(0, count))
        val country = countriesToGuess.removeLast()

        _uiState.value = _uiState.value.copy(currentCountry = country, selectOptions = createUserInput(country), isSelectMode = isSelectMode)
    }

    fun updateUserInput(value: String) {
        _uiState.value = _uiState.value.copy(userInput = value)
    }

    fun handleNextClick() {
        try {
            val nextCountry = countriesToGuess.removeLast()
            _uiState.value = _uiState.value.copy(
                currentCountry = nextCountry,
                userInput = "",
                madeGuess = false,
                guessedCorrect = false,
                selectOptions = createUserInput(nextCountry)
                )
        } catch (e: NoSuchElementException) {
            _uiState.value = _uiState.value.copy(gameOver = true)
        }
    }

    private fun createUserInput(country: Country): List<String> {
        return if (inputMode == SelectedMode.SELECT.toString()) {
            pickThreeRandomCountries(country).shuffled()
        } else {
            emptyList()
        }

    }

    fun handleGuessSubmit() {
        val input = _uiState.value.userInput
        val currentCountry = _uiState.value.currentCountry
        var newUiState = _uiState.value.copy()
        if (currentCountry.name.lowercase().trim() == input.lowercase().trim()) {
            newUiState = newUiState.copy(
                score = _uiState.value.score + 1,
                guessedCorrect = true
            )
        } else {
            newUiState = newUiState.copy(
                guessedCorrect = false
            )
        }
        _uiState.value = newUiState.copy(
            madeGuess = true
        )
    }


}

data class GamePanelUiState(
    val score: Int = 0,
    val userInput: String = "",
    val selectOptions: List<String> = emptyList(),
    val madeGuess: Boolean = false,
    val gameOver: Boolean = false,
    val guessedCorrect: Boolean = false,
    val currentCountry: Country = countries[0],
    val isSelectMode: Boolean = true,
)
