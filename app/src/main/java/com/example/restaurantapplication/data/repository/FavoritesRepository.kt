package com.example.restaurantapplication.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FavoritesRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    private fun userFavsColl(): CollectionReference {
        val uid = auth.currentUser?.uid
            ?: throw IllegalStateException("No Firebase user")
        return FirebaseFirestore.getInstance()
            .collection("restaurant")
            .document("okiniiri")
            .collection("users")
            .document(uid)
            .collection("favorites")
    }
    //监听是否收藏
    fun observeIsFavorite(recipeId: Int): Flow<Boolean> = callbackFlow {
        val reg = userFavsColl().document(recipeId.toString())
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    trySend(false)
                    return@addSnapshotListener
                }
                trySend(snap?.exists() == true)
            }
        awaitClose { reg.remove() }
    }

    //所有列表
    fun observeAllFavorites(): Flow<Set<Int>> = callbackFlow {
        val reg = userFavsColl()
            .addSnapshotListener { qs, e ->
                if (e != null) {
                    trySend(emptySet())
                    return@addSnapshotListener
                }
                val ids = qs?.documents?.mapNotNull { it.id.toIntOrNull() }?.toSet().orEmpty()
                trySend(ids)
            }
        awaitClose { reg.remove() }
    }
    // 切换收藏
    suspend fun toggle(recipeId: Int) = suspendCancellableCoroutine<Unit> { cont ->
        val doc = userFavsColl().document(recipeId.toString())
        db.runTransaction { tx ->
            val snap = tx.get(doc)
            if (snap.exists()) tx.delete(doc)
            else tx.set(
                doc,
                mapOf(
                    "recipeId" to recipeId,
                    "updatedAt" to FieldValue.serverTimestamp()
                )
            )
            null
        }.addOnSuccessListener { cont.resume(Unit) {} }
            .addOnFailureListener { cont.resumeWithException(it) }
    }
}
