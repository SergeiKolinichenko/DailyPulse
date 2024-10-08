package my.mvi.dailypulse.android.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.mvi.dailypulse.articles.ArticlesViewModel

/** Created by Sergei Kolinichenko on 16.09.2024 at 15:22 (GMT+3) **/

@Composable
fun AppScaffold(articlesViewModel: ArticlesViewModel) {
  val navController = rememberNavController()

  Scaffold {
    AppNavHost(
      navController = navController,
      modifier = Modifier
        .fillMaxSize()
        .padding(it),
      articlesViewModel
    )
  }
}

@Composable
fun AppNavHost(
  navController: NavHostController,
  modifier: Modifier = Modifier,
  articlesViewModel: ArticlesViewModel
) {
  NavHost(
    navController = navController,
    startDestination = Screens.ARTICLES.route,
    modifier = modifier,
  ) {
    composable(Screens.ARTICLES.route) {
      ArticlesScreen(
        onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
        articlesViewModel,
      )
    }

    composable(Screens.ABOUT_DEVICE.route) {
      AboutScreen(
        onUpButtonClick = { navController.popBackStack() }
      )
    }
  }
}