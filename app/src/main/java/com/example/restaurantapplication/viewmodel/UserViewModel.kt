package com.example.restaurantapplication.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapplication.data.repository.FavoritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.map


class UserViewModel:  ViewModel()  {

    //Favorites
    private val repoFav = FavoritesRepository()

    // 订阅全部收藏的 id 集合
    val favoriteIds: StateFlow<Set<Int>> =
        repoFav.observeAllFavorites()   // 需要在 repo 里加这个方法
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())

    // 派生：判断某个 recipeId 是否收藏
    fun isFavorite(recipeId: Int): StateFlow<Boolean> =
        favoriteIds
            .map { ids -> recipeId in ids }
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // 切换收藏
    fun toggleFavorite(recipeId: Int) = viewModelScope.launch {
        try {
            repoFav.toggle(recipeId)
        } catch (_: Exception) {
            // TODO: 提示失败
        }
    }

    fun logout() {
//        auth.signOut()
//        _isLoggedIn.value = false //change login state
//        _user.value = null   //empty profile user data
//        Log.d("logout", "User logged out, isLoggedIn = ${_isLoggedIn.value}")
    }
//    fun setCredentialManager(manager: CredentialManager, context: Context) {
//        credentialManager = manager
//        appContext = context.applicationContext
//    }
//
//    fun signInWithGoogle(onResult: (Boolean, String?) -> Unit) {
//        viewModelScope.launch {
//            val googleIdOption = GetGoogleIdOption.Builder()
//                .setFilterByAuthorizedAccounts(false)  // 显示所有可用账号
//                .setServerClientId("970461204290-5otupsb41g98v1a13dovmn3ist7ggqu4.apps.googleusercontent.com")       // 替换成你的 Web Client ID
//                .setAutoSelectEnabled(false)
//                .setNonce(UUID.randomUUID().toString())
//                .build()
//
//            val request = GetCredentialRequest.Builder()
//                .addCredentialOption(googleIdOption)
//                .build()
//
//            try {
//                val result = credentialManager.getCredential(
//                    request = request,
//                    context = appContext
//                )
//
//                val credential = result.credential
//                if (credential is CustomCredential &&
//                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
//                ) {
//                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                    val idToken = googleIdTokenCredential.idToken
//                    if (idToken != null) {
//                        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
//                        auth.signInWithCredential(firebaseCredential)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful) {
//                                    val firebaseUser = auth.currentUser
//                                    firebaseUser?.let {
//                                        val userId = it.uid
//                                        val newUser = User(userId = userId, email = it.email ?: "")
//                                        saveUserData(newUser) { success ->
//                                            if (success) {
//                                                _user.value = newUser
//                                                _isLoggedIn.value = true
//                                                onResult(true, null)
//                                            } else {
//                                                onResult(false, "Failed to save user")
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    onResult(false, task.exception?.message)
//                                }
//                            }
//                    }
//                } else {
//                    onResult(false, "No valid Google credential")
//                }
//            } catch (e: Exception) {
//                onResult(false, e.message)
//            }
//        }
//    }

    fun addToCart(userId:String, recipeId:String, title: String, image: String){}
}