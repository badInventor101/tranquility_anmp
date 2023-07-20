package com.example.tranquility_uas_ubaya_library.view

import android.view.View
import com.example.tranquility_uas_ubaya_library.model.User


interface LoginUserInterface{
    fun onUserLoginClick(v: View) ///di LoginActivity
}

interface RegisUserInterface{
    fun onUserRegisClick(v: View) ///di LoginActivity
}


interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface RentClickListener{
    fun onButtonRentClick(v: View)
}
