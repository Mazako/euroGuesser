package pl.mazak.euroguesser.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pl.mazak.euroguesser.ui.navigation.EuroGuesserNavHost

@Composable
fun EuroGuesserApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    EuroGuesserNavHost(navController, modifier)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EuroGuesserTopBar(
    title: String,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }

    )
}
