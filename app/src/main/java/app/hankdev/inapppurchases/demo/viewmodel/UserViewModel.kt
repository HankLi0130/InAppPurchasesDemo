package app.hankdev.inapppurchases.demo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import app.hankdev.inapppurchases.demo.App.Companion.TAG
import app.hankdev.inapppurchases.demo.model.Plan
import app.hankdev.inapppurchases.demo.model.User
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.logInWith
import com.revenuecat.purchases.models.StoreTransaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppUiState(
    val showAuth: Boolean = true,
    val showAd: Boolean = true
)

class UserViewModel : ViewModel() {
    private var currentUser: User? = null
        set(value) {
            Log.i(TAG, "set currentUser start: ${value?.plan}")
            _appUiState.value = AppUiState(
                value?.token?.isEmpty() != false,
                value?.plan != Plan.PAID
            )
            field = value
            Log.i(TAG, "set currentUser end: ${value?.plan}\n")
        }

    private val _appUiState = MutableStateFlow<AppUiState>(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState.asStateFlow()

    fun login(username: String, password: String) {
        // TODO authenticate user by the username and the password
        val userId = "hankli0130"
        val token = "hank's token"

        Purchases.sharedInstance.logInWith(
            userId,
            onSuccess = { customerInfo, created ->
                Log.i(TAG, "RevenueCat login success")
                val isPremium = customerInfo.entitlements.active.isNotEmpty()
                Log.i(TAG, "is premium: $isPremium")
                currentUser = User(userId, token, if (isPremium) Plan.PAID else Plan.FREE)
            }
        )
    }

    fun onPurchaseCompleted(customerInfo: CustomerInfo, storeTransaction: StoreTransaction) {
        val isPremium = customerInfo.entitlements.active.isNotEmpty()
        currentUser = currentUser!!.copy(plan = if (isPremium) Plan.PAID else Plan.FREE)
    }

    fun onRestoreCompleted(customerInfo: CustomerInfo) {
        val isPremium = customerInfo.entitlements.active.isNotEmpty()
        currentUser = currentUser!!.copy(plan = if (isPremium) Plan.PAID else Plan.FREE)
    }

    fun logout() {
        Purchases.sharedInstance.logOut()
        currentUser = null
    }
}