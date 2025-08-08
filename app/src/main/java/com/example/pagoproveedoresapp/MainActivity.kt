package com.example.pagoproveedoresapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pagoproveedoresapp.ui.theme.PagoProveedoresAppTheme
import com.example.pagoproveedoresapp.ui.theme.screens.ProveedoresScreen
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: PagoViewModel = hiltViewModel()
            PagoProveedoresAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProveedoresScreen(viewModel)
                }
            }
        }
    }
}
