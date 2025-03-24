package app.hankdev.inapppurchases.demo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import app.hankdev.inapppurchases.demo.viewmodel.UserViewModel

@Composable
fun AppScreen(userViewModel: UserViewModel = viewModel()) {
    val uiState by userViewModel.appUiState.collectAsState()

    if (uiState.showAuth) {
        LoginScreen(login = { username, password -> userViewModel.login(username, password) })
    } else {
        MainScreen(showAd = uiState.showAd)
    }
}
