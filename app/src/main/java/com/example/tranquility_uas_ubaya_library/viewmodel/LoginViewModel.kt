package com.example.tranquility_uas_ubaya_library.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.tranquility_uas_ubaya_library.model.AppDatabase
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_1_2
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_2_3
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_3_4
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