package com.krishal.firebaseauth.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.krishal.firebaseauth.AuthState
import com.krishal.firebaseauth.AuthViewModel
import com.krishal.firebaseauth.Constant

@Composable
fun HomePage(navController: NavController,authViewModel: AuthViewModel,modifier: Modifier = Modifier)
{

    val authState=authViewModel.authstate.observeAsState()
    LaunchedEffect(authState.value) {
        when(authState.value)
        {
           is   AuthState.unAuthenticated-> navController.navigate(Constant.login)
            else->Unit
        }
    }
    var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Welcome to Home Page", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Counter: $count", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text(text = "Increase Count")
        }
          TextButton(onClick = {
              authViewModel.signout()
          }) {
              Text(text= "Sign Out")
          }

    }
}