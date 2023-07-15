package com.example.tranquility_uas_ubaya_library.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.util.buildDB
import com.example.tranquility_uas_ubaya_library.view.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application): AndroidViewModel(application), CoroutineScope {


    var userLD = MutableLiveData<User?>()
    var userList = MutableLiveData<List<User>>()

    private val job = Job()

//    lateinit var userDao: UserDao
//    var username: String = "nnejnd"
//    var password: String = "ededed"

    fun login(username: String, password: String) {
        launch {
            val db = buildDB(getApplication())
            userLD.postValue(db.userDao().getUserByUsernamePassword(username, password)) // ke DAO



        }


    }

    fun getAll(){  /// untuk TES
        launch {
            val db = buildDB(getApplication())
            userList.postValue(db.userDao().getAllUser2())

        }

    }




    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}