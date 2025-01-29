package com.krishal.firebaseauth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krishal.firebaseauth.Pages.HomePage
import com.krishal.firebaseauth.Pages.LoginPage
import com.krishal.firebaseauth.Pages.SignUp


@Composable
fun MyApplicationNavigation(authViewModel: AuthViewModel,modifier: Modifier=Modifier)
{
    val navController= rememberNavController()

    NavHost(navController=navController, startDestination = Constant.login, builder = {
        composable(Constant.login){
            LoginPage(navController,authViewModel,modifier)
        }
        composable(Constant.signup){
            SignUp(navController,authViewModel,modifier)
        }
        composable(Constant.home){
            HomePage(navController,authViewModel,modifier)
        }

    })
}