package com.example.tranquility_uas_ubaya_library.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.BookListItemBinding
import com.example.tranquility_uas_ubaya_library.databinding.ItemListItemBinding
import com.example.tranquility_uas_ubaya_library.model.Book

class GenreListAdapter(val BookList:ArrayList<Book>):
    RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>(), ButtonDetailClickListener{ // "ButtonDetailClickListener" sama di interface

    class GenreViewHolder(var view: ItemListItemBinding) : RecyclerView.ViewHolder(view.root) // "ItemListItemBinding" dari layout "item_list_item"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<ItemListItemBinding>(inflater, R.layout.item_list_item, parent, false)

        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
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