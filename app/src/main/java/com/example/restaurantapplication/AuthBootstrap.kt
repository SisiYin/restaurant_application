package com.example.restaurantapplication

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthBootstrap {
    fun ensureSignedIn(
        auth: FirebaseAuth = FirebaseAuth.getInstance(),
        onReady: (FirebaseUser) -> Unit
    ) {
        val cur = auth.currentUser
        if (cur != null) {
            onReady(cur)
            return
        }
        auth.signInAnonymously()
            .addOnSuccessListener { res ->
                res.user?.let(onReady)
            }
            .addOnFailureListener { e ->
                Log.e("Auth", "Anonymous sign-in failed", e)
                // TODO: 这里可以切只读模式的开关
            }
    }

    fun linkWithCredential(
        credential: AuthCredential,
        onDone: (FirebaseUser) -> Unit,
        onErr: (Exception) -> Unit
    ) {
        val user = FirebaseAuth.getInstance().currentUser
            ?: return onErr(IllegalStateException("No user"))
        user.linkWithCredential(credential)
            .addOnSuccessListener { res -> res.user?.let(onDone) }
            .addOnFailureListener(onErr)
    }
}