package com.example.mymatch

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

object AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun haySesion(): Boolean = auth.currentUser != null

    fun correoActual(): String = auth.currentUser?.email ?: ""

    fun iniciarSesion(email: String, clave: String, alTerminar: (String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, clave)
            .addOnSuccessListener { alTerminar(null) }
            .addOnFailureListener { error -> alTerminar(mensajeDeError(error)) }
    }

    fun registrar(email: String, clave: String, alTerminar: (String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, clave)
            .addOnSuccessListener { alTerminar(null) }
            .addOnFailureListener { error -> alTerminar(mensajeDeError(error)) }
    }

    fun recuperarClave(email: String, alTerminar: (String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { alTerminar(null) }
            .addOnFailureListener { error -> alTerminar(mensajeDeError(error)) }
    }

    fun cerrarSesion() {
        auth.signOut()
    }

    private fun mensajeDeError(error: Exception): String {
        return when (error) {
            is FirebaseAuthWeakPasswordException -> "La contraseña es demasiado débil"
            is FirebaseAuthUserCollisionException -> "Ya existe una cuenta con ese correo"
            is FirebaseAuthInvalidUserException -> "No existe ninguna cuenta con ese correo"
            is FirebaseAuthInvalidCredentialsException -> "El correo o la contraseña no son correctos"
            is FirebaseNetworkException -> "No hay conexión a internet"
            else -> "Ha ocurrido un error. Inténtalo de nuevo"
        }
    }
}