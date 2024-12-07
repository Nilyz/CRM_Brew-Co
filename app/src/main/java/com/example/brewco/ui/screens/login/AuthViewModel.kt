package com.example.brewco.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Los campos no pueden estar vacíos")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("El correo no tiene un formato válido")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _authState.value = if (task.isSuccessful) {
                    AuthState.Authenticated
                } else {
                    AuthState.Error(task.exception?.message ?: "Error en el inicio de sesión")
                }
            }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(emailPattern.toRegex())
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


    // Estados de autenticación
    sealed class AuthState {
        object Authenticated : AuthState()
        object Unauthenticated : AuthState()  // Nuevo estado para no autenticado
        object Loading : AuthState()
        data class Error(val message: String) : AuthState()
    }

}
