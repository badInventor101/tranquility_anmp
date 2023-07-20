package com.example.tranquility_uas_ubaya_library.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.model.UserBook
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel


class RentFragment : Fragment() {
    private lateinit var viewModel: BookViewModel // list modelnya
    private val rentAdapter = RentAdapter(arrayListOf()) // ada adapter karena menggunakan recyclerView
    var sharedPref = activity?.getSharedPreferences("usr_key", Context.MODE_PRIVATE)
    // dan datanya adalah arraylist ( banyak )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val par_id = BookListFragmentArgs.fromBundle(requireArguments()).parStr // mengambil parameter ID
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        // cek apakah ada par str dari genre

//        if (par_id != "def"){
//            viewModel.getAllBookByGenre(par_id)
//
//        }
//        else{
//            viewModel.getAllBook()
//        }

        // param di ambil dari shared preferences
        // Retrieve data from SharedPreferences
//        var username = sharedPref?.getString("username", "")
        var username = DataManager.getInstance().username
        viewModel.getAllBookByRent(username!!) // nanti di observe

        val recView = view.findViewById<RecyclerView>(R.id.recViewRent)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = rentAdapter

        observeViewModel()


    }

    fun observeViewModel() {   /// di lihat dan di amati
        viewModel.bookRentLD.observe(viewLifecycleOwner, Observer {
            rentAdapter.updateRentList(it as ArrayList<UserBook>) // lari ke bookListAdapter, di update bookListnya
        })
    }

}