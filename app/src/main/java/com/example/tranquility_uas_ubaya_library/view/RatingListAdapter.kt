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
import com.example.tranquility_uas_ubaya_library.databinding.RatingListItemBinding
import com.example.tranquility_uas_ubaya_library.model.Book

class RatingListAdapter(val RatingList:ArrayList<Book>):
    RecyclerView.Adapter<RatingListAdapter.RatingViewHolder>(){ // "ButtonDetailClickListener" sama di interface

    class RatingViewHolder(var view: RatingListItemBinding) : RecyclerView.ViewHolder(view.root) // "BookListItemBinding" dari layout "book_list_item"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<RatingListItemBinding>(inflater, R.layout.rating_list_item, parent, false)

        return RatingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.view.book = RatingList[position]  /// di bind ke layout nya (databinding)
        // instatiate the listener
//        holder.view.listener = this // dari layout
    }

    override fun getItemCount(): Int {
        return RatingList.size
    }

    fun updateBookList(newAuthorList: ArrayList<Book>) {
        RatingList.clear()
        RatingList.addAll(newAuthorList)
        notifyDataSetChanged()
    }

//    override fun onButtonDetailClick(v: View) {
//        val action = BookListFragmentDirections.actionBookDetail(v.tag.toString()) // di passingkan lewat tag
//        Navigation.findNavController(v).navigate(action)
//    }
}