package pl.mazak.euroguesser.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import pl.mazak.euroguesser.ui.game.GamePanel
import pl.mazak.euroguesser.ui.game.GamePanelRoute
import pl.mazak.euroguesser.ui.mainMenu.MainMenu
import pl.mazak.euroguesser.ui.mainMenu.MainMenuRoute
import pl.mazak.euroguesser.ui.results.ResultsPanel
import pl.mazak.euroguesser.ui.results.ResultsPanelRoute

@Composable
fun EuroGuesserNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = MainMenuRoute.route,
        modifier = modifier
    ) {
        composable(route = MainMenuRoute.route) {
            MainMenu(
                onGameStart = { mode, count ->
                    navController.navigate("${GamePanelRoute.route}/$mode/$count")}
            )
        }

        composable(
            route = GamePanelRoute.routeWithArgs,
            arguments = listOf(
                navArgument(GamePanelRoute.inputModeArg) {
                    type = NavType.StringType
                },
                navArgument(GamePanelRoute.countriesCountArg) {
                    type = NavType.IntType
                }
            )
        ) {
            GamePanel(
                navigateUpCallback = {navController.navigateUp()},
                navigateToResults = { score ->
                    navController.popBackStack()
                    navController.navigate("${ResultsPanelRoute.route}/${score}")
                }
            )
        }

        composable(
            route = ResultsPanelRoute.routeWithArgs,
            arguments = listOf(
                navArgument(ResultsPanelRoute.scoreArg) {
                    type = NavType.IntType
                }
            )
        ) {
            ResultsPanel(
                navigateUpCallback = {navController.navigateUp()}
            )
        }
    }
}
