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
import com.example.tranquility_uas_ubaya_library.model.Book

class AuthorListAdapter(val AuthorList:ArrayList<Book>):
    RecyclerView.Adapter<AuthorListAdapter.AuthorViewHolder>(){ // "ButtonDetailClickListener" sama di interface

    class AuthorViewHolder(var view: AuthorListItemBinding) : RecyclerView.ViewHolder(view.root) // "BookListItemBinding" dari layout "book_list_item"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<AuthorListItemBinding>(inflater, R.layout.author_list_item, parent, false)

        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.view.book = AuthorList[position]  /// di bind ke layout nya (databinding)
        // instatiate the listener
//        holder.view.listener = this // dari layout
    }

    override fun getItemCount(): Int {
        return AuthorList.size
    }

    fun updateBookList(newAuthorList: ArrayList<Book>) {
        AuthorList.clear()
        AuthorList.addAll(newAuthorList)
        notifyDataSetChanged()
    }

//    override fun onButtonDetailClick(v: View) {
//        val action = BookListFragmentDirections.actionBookDetail(v.tag.toString()) // di passingkan lewat tag
//        Navigation.findNavController(v).navigate(action)
//    }
}