package app.hankdev.inapppurchases.demo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.models.StoreTransaction
import com.revenuecat.purchases.ui.revenuecatui.PaywallDialog
import com.revenuecat.purchases.ui.revenuecatui.PaywallDialogOptions
import com.revenuecat.purchases.ui.revenuecatui.PaywallListener

@Composable
fun MainScreen(
    showAd: Boolean,
    onPurchaseCompleted: (customerInfo: CustomerInfo, storeTransaction: StoreTransaction) -> Unit,
    onRestoreCompleted: (customerInfo: CustomerInfo) -> Unit,
    logout: () -> Unit
) {
    var showPaywall by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Main Screen", fontSize = 48.sp)

        if (showAd) {
            Spacer(modifier = Modifier.height(18.dp))
            Text("Ads", fontSize = 48.sp)
        }

        Spacer(modifier = Modifier.height(18.dp))
        Button(onClick = { showPaywall = true }) {
            Text("Upgrade")
        }

        Spacer(modifier = Modifier.height(18.dp))
        Button(onClick = logout) {
            Text("Log out")
        }

        if (showPaywall) {
            PaywallDialog(
                PaywallDialogOptions.Builder()
                    .setRequiredEntitlementIdentifier("premium_v1")
                    .setListener(object : PaywallListener {
                        override fun onPurchaseCompleted(
                            customerInfo: CustomerInfo,
                            storeTransaction: StoreTransaction
                        ) {
                            onPurchaseCompleted(customerInfo, storeTransaction)
                        }

                        override fun onRestoreCompleted(customerInfo: CustomerInfo) {
                            onRestoreCompleted(customerInfo)
                        }
                    })
                    .build()
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        true,
        { customerInfo, storeTransaction -> },
        { customerInfo -> },
        {}
    )
}