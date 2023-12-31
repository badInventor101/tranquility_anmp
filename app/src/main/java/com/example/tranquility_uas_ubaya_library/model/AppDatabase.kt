package com.example.tranquility_uas_ubaya_library.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_1_2
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_2_3
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_3_4
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_4_5

@Database(entities = [User::class, Book::class, UserBook::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    private

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()


        private fun BuildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "librarydb"

            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5) // bisa di koma (tambah)

                .build()


        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: BuildDatabase(context).also {
                        instance = it
                    }
                }
            }

        }
    }



}
