package com.example.tranquility_uas_ubaya_library.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.BookListItemBinding
import com.example.tranquility_uas_ubaya_library.model.Book

class BookListAdapter(val BookList:ArrayList<Book>):
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>(), ButtonDetailClickListener{ // "ButtonDetailClickListener" sama di interface

    class BookViewHolder(var view: BookListItemBinding) : RecyclerView.ViewHolder(view.root) // "BookListItemBinding" dari layout "book_list_item"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater, R.layout.book_list_item, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.book = BookList[position]  /// di bind ke layout nya (databinding)
        // instatiate the listener
        holder.view.listener = this // dari layout


    }

    override fun getItemCount(): Int {
        return BookList.size
    }

    fun updateBookList(newBookList: ArrayList<Book>) {
        BookList.clear()
        BookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = BookListFragmentDirections.actionBookDetail(v.tag.toString()) // di passingkan lewat tag
        Navigation.findNavController(v).navigate(action)
    }
}