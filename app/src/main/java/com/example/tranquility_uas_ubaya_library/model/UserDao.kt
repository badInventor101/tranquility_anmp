package com.example.tranquility_uas_ubaya_library.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserBook(userBook: Book)

//    @Query("SELECT * FROM user WHERE username=:username")
//    suspend fun getUserById(username: String): User?

    @Query("SELECT * FROM user")
    fun getAllUser():User?

    @Query("SELECT * FROM user")
    fun getAllUser2(): List<User> /// untuk TES

    @Query("SELECT * FROM book")
    suspend fun getAllBook(): List<Book>

    @Query("SELECT * FROM user_book")
    suspend fun getAllBookRent(): List<UserBook>

    @Query("SELECT * FROM book WHERE genre = :genre")
    suspend fun getAllBookByGenre(genre: String): List<Book>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun getOneBook(id: Int): Book

    @Query("DELETE FROM book WHERE id = :id")
    suspend fun deleteById(id: Int)


    @Query("INSERT INTO user (name, username, password) VALUES (:name, :username, :password)")
    suspend fun insertUser(name: String, username: String, password: String)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
//    fun getUserByUsernameLiveData(username: String, password: String): LiveData<User?>
//    suspend fun getUserByUsernamePassword(username: String, password: String):User?
    suspend fun getUserByUsernamePassword(username: String, password: String):User
//    suspend fun getUserByUsernamePassword(username: String, password: String):ArrayList<User>
}