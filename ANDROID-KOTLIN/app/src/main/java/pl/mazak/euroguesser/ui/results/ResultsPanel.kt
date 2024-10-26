package pl.mazak.euroguesser.ui.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.mazak.euroguesser.ui.AppViewModelProvider
import pl.mazak.euroguesser.ui.EuroGuesserTopBar
import pl.mazak.euroguesser.ui.mainMenu.MainMenuRoute

object ResultsPanelRoute {
    val route: String = "results"
    val topBarTitle: String = "Results"
    const val scoreArg: String = "score"
    val routeWithArgs: String = "${route}/{${scoreArg}}"
}

@Composable
fun ResultsPanel(
    navigateUpCallback: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ResultsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            EuroGuesserTopBar(
                title = ResultsPanelRoute.topBarTitle,
                canNavigateUp = true,
                navigateUp = navigateUpCallback,
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(innerPadding).fillMaxSize()
        ) {
            Text(text = "Final Score: ${viewModel.score}", fontSize = 48.sp)
        }
    }
}