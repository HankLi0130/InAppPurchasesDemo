package app.hankdev.inapppurchases.demo.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import app.hankdev.inapppurchases.demo.App.Companion.TAG
import app.hankdev.inapppurchases.demo.viewmodel.UserViewModel

@Composable
fun AppScreen(userViewModel: UserViewModel = viewModel()) {
    val uiState by userViewModel.appUiState.collectAsState()

    if (uiState.showAuth) {
        LoginScreen(login = { username, password -> userViewModel.login(username, password) })
    } else {
        Log.i(TAG, "AppScreen show ad: ${uiState.showAd}")
        MainScreen(
            showAd = uiState.showAd,
            onPurchaseCompleted = userViewModel::onPurchaseCompleted,
            onRestoreCompleted = userViewModel::onRestoreCompleted,
            logout = userViewModel::logout
        )
    }
}
