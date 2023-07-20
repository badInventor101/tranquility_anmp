package com.example.tranquility_uas_ubaya_library.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.RatingListItemBinding
import com.example.tranquility_uas_ubaya_library.databinding.RentListItemBinding
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.model.UserBook

class RentAdapter(val RentList:ArrayList<UserBook>):
    RecyclerView.Adapter<RentAdapter.RentViewHolder>() {


    class RentViewHolder(var view: RentListItemBinding) : RecyclerView.ViewHolder(view.root) // "BookListItemBinding" dari layout "book_list_item"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<RentListItemBinding>(inflater, R.layout.rent_list_item, parent, false)

        return RentAdapter.RentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return RentList.size

    }

    override fun onBindViewHolder(holder: RentViewHolder, position: Int) {
        holder.view.rent = RentList[position]  /// di bind ke layout nya (databinding)

    }

    fun updateRentList(newAuthorList: ArrayList<UserBook>) {
        RentList.clear()
        RentList.addAll(newAuthorList)
        notifyDataSetChanged()
    }

}