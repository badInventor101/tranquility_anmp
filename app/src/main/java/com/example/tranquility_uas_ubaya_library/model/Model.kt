package com.example.tranquility_uas_ubaya_library.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "password") val password: String
)
