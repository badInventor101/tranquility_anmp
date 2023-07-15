package com.example.tranquility_uas_ubaya_library.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.ActivityLoginBinding
import com.example.tranquility_uas_ubaya_library.model.AppDatabase
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_1_2
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_2_3
import com.example.tranquility_uas_ubaya_library.viewmodel.LoginViewModel
import com.example.tranquility_uas_ubaya_library.util.buildDB
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), LoginUserInterface {
    private lateinit var binding: ActivityLoginBinding // layoutnya
    private lateinit var viewModel: LoginViewModel
    private lateinit var userDao: UserDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        setContentView(R.layout.activity_login)
        setContentView(view)



//        binding.btnLogin.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this, "binding.btnLogin.text.toString()", Toast.LENGTH_LONG).show()
//
//        })




//        val appDat = buildDB(this)
//        userDao = appDat.userDao()

        /// database
        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "librarydb"
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
        lifecycleScope.launch {
            appDatabase.userDao().getAllUser()
        }
        ///////
//        userDao = appDatabase.userDao()
//
//        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //// BARU
        binding.loginListener = this // dari Interfaces.kt ( merah trus implement di bawah (onUserLoginClick) )




    }

    fun observeUserViewModel(){
        viewModel.userLD.observe(this, Observer {
//            Log.d("AAAAA", it.toString())

            if (it != null){
                Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
                Toast.makeText(this, "suceess", Toast.LENGTH_LONG).show()
                Log.d("AAAAA", "my Message")
            }
            else{
                Toast.makeText(this, "salah", Toast.LENGTH_LONG).show()
                Log.d("AAAAA", "my Message")

            }
//            viewModel.userLD.postValue(null) // untuk clear setelah di observe harus null, karena login hanya TIDAK AND YA

        })
    }

    override fun onUserLoginClick(v: View) {   // dari Interfaces.kt
        viewModel.login(binding.txtInputUsername.text.toString(), binding.txtInputPass.text.toString())     // viewmodel call ke DAO
        Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
//        viewModel.getAll()    // viewmodel call ke DAO
//        Toast.makeText(v.context, binding.txtInputUsername.text, Toast.LENGTH_LONG).show()
//        Toast.makeText(this, "binding.btnLogin.text.toString()", Toast.LENGTH_LONG).show()

//        Log.d("AAAAA", "my Message")

//        observeUserViewModel()

//        Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
//        viewModel.userLD.postValue(null) // untuk clear setelah di observe harus null, karena login hanya TIDAK AND YA

    }

//    fun login() {
//        val username = viewModel.username
//        val password = viewModel.password
//
//        // Observe LiveData version
//        userDao.getUserByUsernameLiveData(username, password).observe(this, { user ->
//            if (user != null && user.password == password) {
//                // Login successful, do something
//                Toast.makeText(this, "sucess", Toast.LENGTH_LONG).show()
//
//            } else {
//                // Invalid username or password, handle error
//                Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}