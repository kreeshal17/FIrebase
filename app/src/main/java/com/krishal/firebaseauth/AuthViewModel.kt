package com.krishal.firebaseauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel:ViewModel() {
  private val auth:FirebaseAuth=FirebaseAuth.getInstance()
    private val _authState= MutableLiveData<AuthState>()
    val authstate:LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus()
    {
        if(auth.currentUser==null)
        {
            _authState.value=AuthState.unAuthenticated
        }
        else{
            _authState.value=AuthState.Authenticated
        }
    }


    fun login(email:String,passwod:String)
    {
        _authState.value= AuthState.Loaading
       if(email.isEmpty()&& passwod.isEmpty())
       {
           _authState.value=AuthState.Error("UserName and Password Cannot be Empty")
           return
       }
        auth.signInWithEmailAndPassword(email,passwod)
            .addOnCompleteListener{
                task->
                if(task.isSuccessful)
                {
                    _authState.value=AuthState.Authenticated
                }
                else{
                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }

    }
    fun signup(email: String,passwod: String)
    {
        _authState.value=AuthState.Loaading
        if(email.isEmpty()&&passwod.isEmpty())
        {
            _authState.value=AuthState.Error("Fields cannot be Empty")
            return
        }
        auth.createUserWithEmailAndPassword(email,passwod).addOnCompleteListener{

            task-> if(task.isSuccessful)
        {
                _authState.value=AuthState.Authenticated
        }
            else
        {
            _authState.value=AuthState.Error(task.exception?.message?:"Something went Wrong")
        }
        }

    }
    fun signout()
    {
        _authState.value=AuthState.unAuthenticated
    }


}


sealed class AuthState
{
    object Authenticated:AuthState()
    object unAuthenticated:AuthState()
    object Loaading :AuthState()
    data class Error(val message:String):AuthState()
}