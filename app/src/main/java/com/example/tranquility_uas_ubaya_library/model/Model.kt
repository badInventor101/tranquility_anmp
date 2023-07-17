package com.example.tranquility_uas_ubaya_library.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey





@Entity(tableName = "user")
data class User(
    @PrimaryKey var username: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "password") var password: String
)

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "ratings") var ratings: Double,
    @ColumnInfo(name = "genre") var genre: String = "",
    @ColumnInfo(name = "language") var language: String = "",
    @ColumnInfo(name = "release") var release: String = "",
    @ColumnInfo(name = "photoUrl") var photoUrl: String = "",
    @ColumnInfo(name = "desc") var desc: String = "",
    @ColumnInfo(name = "stock") var stock: Int = 10
)

@Entity(tableName = "user_book")
data class UserBook (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "bookname") var bookname: String,
    @ColumnInfo(name = "date") var date: String
)
