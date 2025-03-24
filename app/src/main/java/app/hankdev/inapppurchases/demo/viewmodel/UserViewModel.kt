package app.hankdev.inapppurchases.demo.viewmodel

import androidx.lifecycle.ViewModel
import app.hankdev.inapppurchases.demo.model.Plan
import app.hankdev.inapppurchases.demo.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AppUiState(
    val showAuth: Boolean = true,
    val showAd: Boolean = true
)

class UserViewModel : ViewModel() {
    private var currentUser: User = User()

    private val _appUiState = MutableStateFlow<AppUiState>(AppUiState())
    val appUiState: StateFlow<AppUiState> get() = _appUiState

    private fun updateCurrentUser(user: User) {
        currentUser = user
        _appUiState.value = AppUiState(
            showAuth = user.token.isEmpty(),
            showAd = user.plan == Plan.FREE
        )
    }

    fun login(username: String, password: String) {
        // TODO authenticate user by the username and the password
        updateCurrentUser(currentUser.copy(id = "hankli0130", token = "hank's token"))
    }
}