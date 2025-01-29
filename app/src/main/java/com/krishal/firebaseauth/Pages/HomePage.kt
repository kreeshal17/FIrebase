package com.krishal.firebaseauth.Pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.krishal.firebaseauth.AuthState
import com.krishal.firebaseauth.AuthViewModel
import com.krishal.firebaseauth.Constant

@OptIn(ExperimentalMaterial3Api::class)
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
    var scrollbehaviour= TopAppBarDefaults.enterAlwaysScrollBehavior()
    var textf by remember {
        mutableStateOf(false)
    }
    var ans by remember {
        mutableStateOf("")
    }
    Scaffold(modifier = Modifier.fillMaxSize().nestedScroll(scrollbehaviour.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(text="list")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "this is icon")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "this is icon")
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "this is icon")
                    }


                },
                scrollBehavior = scrollbehaviour
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "this is icon")}
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = { authViewModel.signout() },
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(0.8f) // Adjust width
                            ,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Red, // Sexy red color
                            contentColor = Color.White // White text for contrast
                        ),
                        shape = RoundedCornerShape(30.dp), // Rounded edges
                        elevation = ButtonDefaults.buttonElevation(8.dp) // Sexy shadow
                    ) {
                        Text(
                            text = "🚀 Sign Out",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = "this is icon")
                    }
                    Spacer(modifier = Modifier.weight(1f))



                }, floatingActionButton = {
                    FloatingActionButton(onClick = { }) {
                        IconButton(onClick = {
                            textf=!textf
                        }) {
                            Icon(imageVector =Icons .Default.Add, contentDescription = "this is icon")
                        }
                    }
                }




            )
        }


    )




    {  paddingValues ->
        if(textf)
        {
            Box(modifier = Modifier.fillMaxWidth().padding(paddingValues))
            {
                OutlinedTextField(value=ans, onValueChange = {ans=it}, label = { Text(text="enter note")})
            }
        }
        else {
            var image by remember {
                mutableStateOf<Uri?>(null)
            }

            val iamgePickerLauncher= rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = {image=it}
            )

              Column(modifier=modifier.fillMaxSize().padding(paddingValues), verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally) {

                  AsyncImage(
                      model = image,
                      contentDescription = null,
                      modifier=modifier.height(200.dp).fillMaxWidth()

                  )

                  Button(onClick = {
                         iamgePickerLauncher.launch(
                             PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)

                         )
                     }) {
                         Text(text="Upload Image")

                     }








              }
            }












        }



    }
