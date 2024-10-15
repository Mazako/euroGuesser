package pl.mazak.euroguesser.ui.game

import android.content.res.Resources.Theme
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.processNextEventInCurrentThread
import pl.mazak.euroguesser.R
import pl.mazak.euroguesser.data.countries
import pl.mazak.euroguesser.ui.AppViewModelProvider
import pl.mazak.euroguesser.ui.EuroGuesserTopBar
import pl.mazak.euroguesser.ui.mainMenu.MainMenuRoute
import pl.mazak.euroguesser.ui.mainMenu.SelectedMode
import pl.mazak.euroguesser.ui.theme.EuroGuesserTheme
import pl.mazak.euroguesser.ui.theme.PurpleGrey40
import pl.mazak.euroguesser.ui.theme.PurpleGrey80


object GamePanelRoute {
    val route: String = "game"
    val topBarTitle: String = "Game"
    const val inputModeArg: String = "input"
    const val countriesCountArg: String = "count"
    val routeWithArgs: String = "${route}/{${inputModeArg}}/{${countriesCountArg}}"
}

@Composable
fun GamePanel(
    navigateUpCallback: () -> Unit,
    navigateToResults: (score: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GamePanelViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value.gameOver) {
        if (uiState.value.gameOver) {
            navigateToResults(uiState.value.score)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            EuroGuesserTopBar(
                title = GamePanelRoute.topBarTitle,
                canNavigateUp = true,
                navigateUp = navigateUpCallback,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                "Score: ${uiState.value.score}"
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(0.45f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = uiState.value.currentCountry.flag,
                    fontSize = 130.sp,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                if (uiState.value.madeGuess) {
                    if (uiState.value.guessedCorrect) {
                        Text("You guessed correct", textAlign = TextAlign.Center)
                    } else {
                        Text("Wrong. Correct country is: ${uiState.value.currentCountry.name}", textAlign = TextAlign.Center)
                    }
                } else {
                    if (uiState.value.isSelectMode) {
                        SelectInput(
                            countries = uiState.value.selectOptions,
                            onCountryClick = { v -> viewModel.updateUserInput(v) },
                            currentInput = uiState.value.userInput,
                        )
                    } else {
                        ManualInput(
                            value = uiState.value.userInput,
                            onValueChange = {v -> viewModel.updateUserInput(v)}
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .weight(0.05f)
                    .fillMaxWidth()
            ) {
                if (uiState.value.madeGuess) {
                    Button(
                        onClick = {viewModel.handleNextClick()},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = {viewModel.handleGuessSubmit()},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Check")
                    }
                }
            }
        }
    }
}


@Composable
fun ManualInput(
    value: String,
    onValueChange: (v: String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text("Country")
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
    )
}

@Composable
fun SelectInput(
    countries: List<String>,
    onCountryClick: (v: String) -> Unit,
    currentInput: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
        ) {
            CountryButton(
                onClick = onCountryClick,
                userInput = currentInput,
                text = countries[0],
                modifier = Modifier.weight(0.5f)
            )
            CountryButton(
                onClick = onCountryClick,
                userInput = currentInput,
                text = countries[1],
                modifier = Modifier.weight(0.5f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
        ) {
            CountryButton(
                onClick = onCountryClick,
                userInput = currentInput,
                text = countries[2],
                modifier = Modifier.weight(0.5f)
            )
            CountryButton(
                onClick = onCountryClick,
                userInput = currentInput,
                text = countries[3],
                modifier = Modifier.weight(0.5f)
            )
        }
    }

}


@Composable
fun CountryButton(
    text: String,
    onClick: (v: String) -> Unit,
    userInput: String,
    modifier: Modifier
) {
    var colors = ButtonDefaults.buttonColors()
    if (userInput == text) {
        colors = ButtonDefaults.buttonColors().copy(containerColor = PurpleGrey40)
    }
    Button(
        onClick = { onClick(text) },
        colors = colors,
        shape = RectangleShape,
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Text(text)
    }

}

@Composable
@Preview
fun GamePanelPreview() {
    EuroGuesserTheme {
        GamePanel({}, {s -> {}})
    }
}