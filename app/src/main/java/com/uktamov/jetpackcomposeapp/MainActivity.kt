package com.uktamov.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uktamov.jetpackcomposeapp.ui.component.CustomToolbar
import com.uktamov.jetpackcomposeapp.ui.detail.DetailEvent
import com.uktamov.jetpackcomposeapp.ui.detail.DetailScreen
import com.uktamov.jetpackcomposeapp.ui.detail.DetailViewModel
import com.uktamov.jetpackcomposeapp.ui.home.HomeScreen
import com.uktamov.jetpackcomposeapp.ui.home.HomeViewModel
import com.uktamov.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAppTheme {
                val navController = rememberNavController()
                var title by remember {
                    mutableStateOf("Home")
                }

                Scaffold(
                    topBar = {
                        CustomToolbar(
                            title = title,
                            isBackIconVisible = title != "Home",
                            onBack = {
                                navController.popBackStack()
                                title = "Home"
                            }
                        )
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home_screen",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(route = "home_screen") {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            val state by homeViewModel.state.collectAsState()
                            HomeScreen(
                                uiState = state,
                                onClick = {
                                    navController.navigate("detail_screen/$it")
                                    title = "User Details"
                                }
                            )
                        }
                        composable(route = "detail_screen/{id}") {
                            val detailViewModel = hiltViewModel<DetailViewModel>()
                            val detailState by detailViewModel.state.collectAsState()
                            val id = it.arguments?.getString("id") ?: "1"
                            DetailScreen(
                                uiState = detailState,
                                onEvent = {
                                    detailViewModel.onEvent(DetailEvent.OnGetUser(id.toInt()))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

