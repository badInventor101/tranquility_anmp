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
    @ColumnInfo(name = "name") var name: String = "Mama",
    @ColumnInfo(name = "author") var author: String = "Ruby Jean Jensen",
    @ColumnInfo(name = "ratings") var ratings: Double = 3.9,
    @ColumnInfo(name = "genre") var genre: String = "Horror",
    @ColumnInfo(name = "language") var language: String = "English",
    @ColumnInfo(name = "release") var release: String = "1983",
    @ColumnInfo(name = "photoUrl") var photoUrl: String = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1490108730i/2160474.jpg",
    @ColumnInfo(name = "desc") var desc: String = "Just a horror book",
    @ColumnInfo(name = "stock") var stock: Int = 100
)


