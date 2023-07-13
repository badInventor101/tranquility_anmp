package com.example.tranquility_uas_ubaya_library.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {


    val userLD = MutableLiveData<User>()

    private val job = Job()

//    lateinit var userDao: UserDao
    var username: String = ""
    var password: String = ""

    fun login(username: String, password: String) {
        launch {
            val db = buildDB(getApplication())
            userLD.postValue(db.userDao().getUserByUsernamePassword(username, password))

        }


    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}