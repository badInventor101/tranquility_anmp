package com.example.tranquility_uas_ubaya_library.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.tranquility_uas_ubaya_library.model.*
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

class BookViewModel(application: Application): AndroidViewModel(application), CoroutineScope {


    var bookLD =  MutableLiveData<List<Book>>()
    var bookRentLD =  MutableLiveData<List<UserBook>>()

    var bookOneLD = MutableLiveData<Book?>()
    var userList = MutableLiveData<List<User>>()

    var UserbookLD =  MutableLiveData<List<UserBook>>()


    private val job = Job()

//    lateinit var userDao: UserDao
//    var username: String = "nnejnd"
//    var password: String = "ededed"




    fun getAllBook(){
        launch {
            val db = buildDB(getApplication())
            bookLD.postValue(db.userDao().getAllBook())

        }

    }

    fun getAllBookByGenre(gen: String){
        launch {
            val db = buildDB(getApplication())
            bookLD.postValue(db.userDao().getAllBookByGenre(gen))

        }

    }

    fun getAllBookByRent(username: String){
        launch {
            val db = buildDB(getApplication())
            bookRentLD.postValue(db.userDao().getAllBookRent(username))

        }

    }

    fun insertRentBook(username: String, bookname: String, date: String){
        launch {
            val db = buildDB(getApplication())
            db.userDao().insertBookRent(username, bookname, date)
            // kurangi buku
            db.userDao().decrementBookStock(bookname, 1)

        }

    }


    fun getOneBook(id: Int){
        launch {
            val db = buildDB(getApplication())
            bookOneLD.postValue(db.userDao().getOneBook(id))

        }
    }






    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}