package com.example.tranquility_uas_ubaya_library.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.FragmentBookDetailBinding
import com.example.tranquility_uas_ubaya_library.databinding.FragmentProfileBinding
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel
import com.example.tranquility_uas_ubaya_library.viewmodel.LoginViewModel
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel // list modelnya
    private lateinit var dataBinding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val user_username = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId // mengambil parameter ID
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        var username = DataManager.getInstance().username // untuk mendapatkan username
        viewModel.getOneUser(username)

        // dan akhirnya di observe datanya setelah di fetch dari DetailViewModel
        observeViewModel()
    }

    fun observeViewModel() {   /// di lihat dan di amati
        /// bisa juga di ambil datanya
        viewModel.userOneLD.observe(viewLifecycleOwner, Observer {
            dataBinding.userD = it
        })
    }
}