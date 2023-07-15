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


