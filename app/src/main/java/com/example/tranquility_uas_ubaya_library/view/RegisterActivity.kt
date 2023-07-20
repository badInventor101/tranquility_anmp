package com.example.tranquility_uas_ubaya_library.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.ActivityLoginBinding
import com.example.tranquility_uas_ubaya_library.databinding.ActivityRegisterBinding
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.viewmodel.LoginViewModel

class RegisterActivity : AppCompatActivity(), RegisUserInterface {
    private lateinit var binding: ActivityRegisterBinding // layoutnya
    private lateinit var viewModel: LoginViewModel
    private lateinit var userDao: UserDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        setContentView(R.layout.activity_login)
        setContentView(view)

        binding.lifecycleOwner = this

        //// BARU
        binding.regisListener = this //
    }

    fun observeUserViewModel(){
        viewModel.userLD.observe(this, Observer {
//            Log.d("AAAAA", it.toString())

            if (it?.username != null){
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

    override fun onUserRegisClick(v: View) {
        if (binding.txtusrnameReg.text.toString() == "" || binding.txtnameReg.text.toString() == "" || binding.txtpassReg.text.toString() == ""){
            Toast.makeText(this, "Semua field please di isi", Toast.LENGTH_LONG).show()


        }
        else{
            viewModel.register(binding.txtnameReg.text.toString(), binding.txtusrnameReg.text.toString(), binding.txtpassReg.text.toString())     // viewmodel call ke DAO
            Toast.makeText(this, "Silahkan Login Terlebih Dahulu", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }


    }
}