package com.example.tranquility_uas_ubaya_library.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.viewmodel.AuthorViewModel
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel


class DateListFragment : Fragment() {
    private lateinit var viewModel: BookViewModel // list modelnya
    private val dateListAdapter = DateListAdapter(arrayListOf()) // ada adapter karena menggunakan recyclerView
    // dan datanya adalah arraylist ( banyak )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_list, container, false)
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


        viewModel.getAllBook() // nanti di observe

        val recView = view.findViewById<RecyclerView>(R.id.recViewDate)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = dateListAdapter

        observeViewModel()


    }

    fun observeViewModel() {   /// di lihat dan di amati
        viewModel.bookLD.observe(viewLifecycleOwner, Observer {
            dateListAdapter.updateBookList(it as ArrayList<Book>) // lari ke bookListAdapter, di update bookListnya
        })
    }

}




