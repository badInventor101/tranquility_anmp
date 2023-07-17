package com.example.tranquility_uas_ubaya_library.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.FragmentBookDetailBinding
import com.example.tranquility_uas_ubaya_library.viewmodel.BookViewModel


class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookViewModel // list modelnya
    private lateinit var dataBinding:FragmentBookDetailBinding


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

        // dan akhirnya di observe datanya setelah di fetch dari DetailViewModel
        observeViewModel()


    }

    fun observeViewModel() {   /// di lihat dan di amati
        /// bisa juga di ambil datanya
        viewModel.bookOneLD.observe(viewLifecycleOwner, Observer {
            dataBinding.bookD = it

        })





    }





}