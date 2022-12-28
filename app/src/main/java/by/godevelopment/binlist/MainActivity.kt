package by.godevelopment.binlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.godevelopment.binlist.ui.navigation.NavigationConstVal.NAV_ARGUMENT_LABEL
import by.godevelopment.binlist.ui.navigation.Route
import by.godevelopment.binlist.ui.screens.details.DetailsScreen
import by.godevelopment.binlist.ui.screens.home.HomeScreen
import by.godevelopment.binlist.ui.theme.BinlistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BinlistTheme {

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    topBar = {}
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME.label
                    ) {
                        composable(Route.HOME.label) {
                            HomeScreen(
                                scaffoldState = scaffoldState,
                                contentPadding = padding,
                                onClickItem = { number ->
                                    navController.navigate(Route.DETAILS.label + "/$number") {
                                        popUpTo(Route.HOME.label)
                                    }
                                }
                            )
                        }
                        composable(
                            route = Route.DETAILS.label + "/{" + NAV_ARGUMENT_LABEL + "}",
                            arguments = listOf(navArgument(NAV_ARGUMENT_LABEL) { type = NavType.StringType })
                        ) {
                            DetailsScreen(
                                scaffoldState = scaffoldState,
                                contentPadding = padding,
                                onClickBack = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
