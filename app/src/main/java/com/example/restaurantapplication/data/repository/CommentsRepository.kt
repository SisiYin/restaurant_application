package com.example.restaurantapplication.data.repository

import com.example.restaurantapplication.data.model.Comment
import com.example.restaurantapplication.data.model.UserCommentRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.FieldValue

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class CommentsRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val rid: String = "okiniiri"
) {
    private fun dishDoc(dishId: String) =
        db.collection("restaurant").document(rid)
            .collection("dishes").document(dishId)

    private fun dishComments(dishId: String) = dishDoc(dishId).collection("comments")

    private fun userComments(uid: String) =
        db.collection("restaurant").document(rid)
            .collection("users").document(uid)
            .collection("comments")

    // 按菜监听评论列表（详情页）
    fun listenByDish(dishId: String): Flow<List<Comment>> = callbackFlow {
        val reg = dishComments(dishId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { qs, e ->
                if (e != null) { trySend(emptyList()); return@addSnapshotListener }
                val list = qs?.documents.orEmpty().map { d ->
                    Comment(
                        id = d.id,
                        dishId = dishId,
                        uid = d.getString("uid"),
                        nickname = d.getString("nickname"),
                        text = d.getString("text") ?: "",
                        rating = d.getLong("rating")?.toInt(),
                        createdAt = d.getTimestamp("createdAt"),
                        updatedAt = d.getTimestamp("updatedAt")
                    )
                }
                trySend(list)
            }
        awaitClose { reg.remove() }
    }

    // 按用户监听“我的评论”（Profile）
    fun listenByUser(): Flow<List<UserCommentRef>> = callbackFlow {
        val uid = auth.currentUser?.uid ?: return@callbackFlow
        val reg = userComments(uid)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { qs, e ->
                if (e != null) { trySend(emptyList()); return@addSnapshotListener }
                val list = qs?.documents.orEmpty().map { d ->
                    UserCommentRef(
                        id = d.id,
                        dishId = d.getString("dishId") ?: "",
                        rating = d.getLong("rating")?.toInt(),
                        textPreview = d.getString("textPreview"),
                        createdAt = d.getTimestamp("createdAt")
                    )
                }
                trySend(list)
            }
        awaitClose { reg.remove() }
    }

    // 发布或更新当前用户在该菜下的评论（同一道菜只保留一条）
    suspend fun postOrUpdate(
        dishId: String,
        text: String,
        rating: Int?,
        nickname: String?
    ) {
        val uid = auth.currentUser?.uid ?: error("No user")
        val comments = dishComments(dishId)
        val now = FieldValue.serverTimestamp()

        // 查是否已有该用户的评论
        val existing = comments.whereEqualTo("uid", uid).limit(1).get().await()

        db.runBatch { b ->
            if (!existing.isEmpty) {
                val doc = existing.documents.first().reference
                b.update(doc, mapOf(
                    "text" to text,
                    "rating" to rating,
                    "nickname" to nickname,
                    "updatedAt" to now
                ))
                val idx = userComments(uid).document(doc.id)
                b.set(idx, mapOf(
                    "dishId" to dishId,
                    "rating" to rating,
                    "textPreview" to text.take(50),
                    "createdAt" to now
                ), SetOptions.merge())
            } else {
                val doc = comments.document()
                b.set(doc, mapOf(
                    "uid" to uid,
                    "nickname" to nickname,
                    "text" to text,
                    "rating" to rating,
                    "createdAt" to now,
                    "updatedAt" to now
                ))
                val idx = userComments(uid).document(doc.id)
                b.set(idx, mapOf(
                    "dishId" to dishId,
                    "rating" to rating,
                    "textPreview" to text.take(50),
                    "createdAt" to now
                ))
            }
        }
    }

    suspend fun delete(dishId: String, commentId: String) {
        val uid = auth.currentUser?.uid ?: error("No user")
        db.runBatch { b ->
            b.delete(dishComments(dishId).document(commentId))
            b.delete(userComments(uid).document(commentId))
        }.await()
    }
}
