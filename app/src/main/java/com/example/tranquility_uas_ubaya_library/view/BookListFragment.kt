package com.example.tranquility_uas_ubaya_library.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BookListFragment : Fragment() {
    private lateinit var viewModel: BookViewModel // list modelnya
    private val bookListAdapter = BookListAdapter(arrayListOf()) // ada adapter karena menggunakan recyclerView
    private lateinit var sharedPrefs: SharedPreferences
    // dan datanya adalah arraylist ( banyak )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = requireActivity().getSharedPreferences(requireActivity().getPackageName(), Context.MODE_PRIVATE)


        // saat logout di click
        val fabLogout = view.findViewById<FloatingActionButton>(R.id.fabLogout)
        fabLogout.setOnClickListener {
            // kosogi data manager (global)
            DataManager.getInstance().username = ""
            // sharedPrefences juga
            // SHARED KEY
            var SHARED_USERNAME = "usr"
            //
            val editor = sharedPrefs.edit{
                putString(SHARED_USERNAME, "")


            }
            ///

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)



        }

        val par_id = BookListFragmentArgs.fromBundle(requireArguments()).parStr // mengambil parameter ID
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        // cek apakah ada par str dari genre


        if (par_id != "def"){
            viewModel.getAllBookByGenre(par_id)

        }
        else{
            viewModel.getAllBook()
        }


        viewModel.getAllBook() // nanti di observe

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = bookListAdapter

        observeViewModel()


    }

    fun observeViewModel() {   /// di lihat dan di amati
        viewModel.bookLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it as ArrayList<Book>) // lari ke bookListAdapter, di update bookListnya
        })
    }

}




