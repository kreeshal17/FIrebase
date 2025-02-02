package com.krishal.firebaseauth.Pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.krishal.firebaseauth.AuthState
import com.krishal.firebaseauth.AuthViewModel
import com.krishal.firebaseauth.Constant

@Composable
fun SignUp(navController: NavController, authViewModel:AuthViewModel,modifier: Modifier =Modifier)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authstate =authViewModel.authstate.observeAsState()
    val context= LocalContext.current
    LaunchedEffect(authstate.value) {
        when(authstate.value)
        {
            AuthState.Authenticated -> navController.navigate(Constant.home)
            is AuthState.Error -> Toast.makeText(context,
                (authstate.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
 else ->Unit
        }






    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text="SignUp", style = TextStyle.Default,fontSize =30.sp
        )
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value=email, onValueChange = {email=it}, label = { Text(text="enter the Email") })
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value=password, onValueChange = {password=it}, label = { Text(text="enter the password") })
        Spacer(modifier=Modifier.height(16.dp))
        Button(onClick = {
            authViewModel.signup(email,password)
        }) {

            Text(text="Sign-Up")
        }
        Spacer(modifier=Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate(Constant.login)}) {
            Text(text="Already have an account,Log In")
        }




    }


}