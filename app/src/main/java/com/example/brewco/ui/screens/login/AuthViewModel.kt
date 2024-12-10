package com.example.brewco.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun login(email: String, password: String) {
        // Validaciones previas
        if (email.isEmpty() && password.isEmpty()) {
            _authState.value = AuthState.Error("Los campos no pueden estar vacíos")
            return
        }

        if (email.isEmpty()) {
            _authState.value = AuthState.Error("Introduce un correo")
            return
        }

        if (password.isEmpty()) {
            _authState.value = AuthState.Error("Introduce la contraseña")
            return
        }

        if (!isValidEmail(email)) {
            _authState.value = AuthState.Error("El correo no tiene un formato válido")
            return
        }

        _authState.value = AuthState.Loading

        // Intento de iniciar sesión con Firebase
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Si la autenticación es exitosa
                    _authState.value = AuthState.Authenticated
                } else {
                    // Si el login falla, mostramos un mensaje genérico
                    _authState.value = AuthState.Error("Correo o contraseña incorrecta")
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
