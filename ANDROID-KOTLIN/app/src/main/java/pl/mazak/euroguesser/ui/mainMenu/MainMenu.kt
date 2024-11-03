package pl.mazak.euroguesser.ui.mainMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.mazak.euroguesser.data.countries
import pl.mazak.euroguesser.ui.AppViewModelProvider
import pl.mazak.euroguesser.ui.EuroGuesserTopBar
import pl.mazak.euroguesser.ui.theme.EuroGuesserTheme

object MainMenuRoute {
    val route: String = "mainMenu"
    val topBarTitle = "Main Menu"
}

@Composable
fun MainMenu(
    onGameStart: (mode: SelectedMode, count: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainMenuViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val selectedMode = viewModel.selectedMode

    val count = viewModel.count
    val countValid = viewModel.isCountValid()
    val placeHolderMsg: String = if (countValid.first) {
        "Number of countries"
    } else {
        countValid.second!!
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            EuroGuesserTopBar(
                title = MainMenuRoute.topBarTitle,
                canNavigateUp = false,
                navigateUp = { },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column {
                Text("Data entry mode:")
                InputModeRadioButton(name = "Select the correct one",
                    selected = selectedMode == SelectedMode.SELECT,
                    onClick = {viewModel.updateSelectedMode(SelectedMode.SELECT)}
                )
                InputModeRadioButton(
                    name = "Manual input",
                    selected = selectedMode == SelectedMode.WRITE,
                    onClick = {viewModel.updateSelectedMode(SelectedMode.WRITE)}
                )

            }
            Spacer(modifier = Modifier.size(36.dp))
            Column {
                Text("Number of countries (max ${countries.size}):")
                TextField(
                    value = count,
                    onValueChange = {e -> viewModel.updateCount(e) },
                    label = {
                        Text(placeHolderMsg)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    isError = !countValid.first,
                )
            }
            Spacer(modifier = Modifier.size(36.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    enabled = countValid.first,
                    onClick = { onGameStart(selectedMode, count) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start")
                }
            }
        }
    }
}


@Composable
fun InputModeRadioButton(name: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }

}



@Preview
@Composable
fun MainMenuPreview() {
    return EuroGuesserTheme {
        MainMenu({a, b -> run {} })
    }
}