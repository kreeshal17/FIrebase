package com.krishal.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.krishal.firebaseauth.ui.theme.FIrebaseAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel:AuthViewModel by viewModels()
        setContent {
            FIrebaseAuthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MyApplicationNavigation(authViewModel,Modifier.padding(innerPadding))
                }
            }
        }
    }
}

