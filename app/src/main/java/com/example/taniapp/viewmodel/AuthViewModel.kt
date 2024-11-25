package com.example.taniapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> get() = _authState

    init {
        checkAuthStatus()
    }

    private fun updateAuthState() {
        _authState.postValue(
            if (auth.currentUser == null) {
                AuthState.Unauthenticated
            } else {
                AuthState.Authenticated
            }
        )
    }

    fun checkAuthStatus() {
        updateAuthState()
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email atau password belum diisi!")
            return
        }

        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateAuthState()
                } else {
                    _authState.value = AuthState.Error(
                        task.exception?.localizedMessage ?: "Gagal masuk. Coba lagi nanti."
                    )
                }
            }
    }

    fun signup(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Email atau password belum diisi!")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateAuthState()
                } else {
                    _authState.value = AuthState.Error(
                        task.exception?.localizedMessage ?: "Gagal mendaftar. Coba lagi nanti."
                    )
                }
            }
    }

    fun signout() {
        auth.signOut()
        updateAuthState()
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}