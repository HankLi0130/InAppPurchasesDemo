package app.hankdev.inapppurchases.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.hankdev.inapppurchases.demo.ui.screen.AppScreen
import app.hankdev.inapppurchases.demo.ui.theme.InAppPurchasesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InAppPurchasesDemoTheme {
                AppScreen()
            }
        }
    }
}
