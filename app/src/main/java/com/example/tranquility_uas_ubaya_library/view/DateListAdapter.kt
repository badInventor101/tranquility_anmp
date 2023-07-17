package com.example.tranquility_uas_ubaya_library.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.AuthorListItemBinding
import com.example.tranquility_uas_ubaya_library.databinding.BookListItemBinding
import com.example.tranquility_uas_ubaya_library.databinding.DateListItemBinding
import com.example.tranquility_uas_ubaya_library.databinding.LanguageListItemBinding
import com.example.tranquility_uas_ubaya_library.model.Book

class DateListAdapter(val DateList:ArrayList<Book>):
    RecyclerView.Adapter<DateListAdapter.DateViewHolder>(){ // "ButtonDetailClickListener" sama di interface

    class DateViewHolder(var view: DateListItemBinding) : RecyclerView.ViewHolder(view.root) // "BookListItemBinding" dari layout "book_list_item"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<DateListItemBinding>(inflater, R.layout.date_list_item, parent, false)

        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.view.book = DateList[position]  /// di bind ke layout nya (databinding)
        // instatiate the listener
//        holder.view.listener = this // dari layout
    }

    override fun getItemCount(): Int {
        return DateList.size
    }

    fun updateBookList(newAuthorList: ArrayList<Book>) {
        DateList.clear()
        DateList.addAll(newAuthorList)
        notifyDataSetChanged()
    }

//    override fun onButtonDetailClick(v: View) {
//        val action = BookListFragmentDirections.actionBookDetail(v.tag.toString()) // di passingkan lewat tag
//        Navigation.findNavController(v).navigate(action)
//    }
}