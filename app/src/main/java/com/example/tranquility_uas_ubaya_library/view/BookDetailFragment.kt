package com.example.tranquility_uas_ubaya_library.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.FragmentBookDetailBinding
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel
import com.example.tranquility_uas_ubaya_library.viewmodel.LoginViewModel
import java.text.SimpleDateFormat
import java.util.*


class BookDetailFragment : Fragment(), RentClickListener {
    private lateinit var viewModel: BookViewModel // list modelnya
    private lateinit var dataBinding:FragmentBookDetailBinding

//    var sharedPref = activity?.getSharedPreferences("usr_key", Context.MODE_PRIVATE)
    // param di ambil dari shared preferences
    // Retrieve data from SharedPreferences
//    var username = sharedPref?.getString("username", "")
    // global data
//    var username = DataManager.getInstance().username



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_book_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentBookDetailBinding>(inflater, R.layout.fragment_book_detail, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book_id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId // mengambil parameter ID
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.getOneBook(book_id.toInt()) // passingkan ID

        dataBinding.listener = this

        var username = DataManager.getInstance().username // untuk mendapatkan username
















        // dan akhirnya di observe datanya setelah di fetch dari DetailViewModel
        observeViewModel()


    }

    fun observeViewModel() {   /// di lihat dan di amati
        /// bisa juga di ambil datanya
        viewModel.bookOneLD.observe(viewLifecycleOwner, Observer {
            dataBinding.bookD = it

        })





    }

    override fun onButtonRentClick(v: View) {
        // date
        val calendar: Calendar = Calendar.getInstance()
        val currentDate: Date = calendar.getTime()
        // Increment the date by 10 days
        calendar.add(Calendar.DAY_OF_MONTH, 10)
        val futureDate: Date = calendar.getTime()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val currentDateString: String = dateFormat.format(currentDate)
        var futureDateString: String = dateFormat.format(futureDate)
//        //////////
//
//        var sharedPref : SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

        // get user session
        // SHARED KEY
//        var SHARED_USERNAME = "usr"
        //

        // retrieve user data
//        var username = sharedPref.getString(SHARED_USERNAME, "aa") // default cond ""
////        // param di ambil dari shared preferences
        // user data get from DataManager (Globall jangan lupa di kosongi pas logout)
        var username = DataManager.getInstance().username // untuk mendapatkan username
        viewModel.insertRentBook(username!!, dataBinding.txtTitle.text.toString(), futureDateString)    // viewmodel call ke DAO


        Toast.makeText(activity,"success rent", Toast.LENGTH_LONG).show()


    }


}