package com.example.tranquility_uas_ubaya_library.util


import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tranquility_uas_ubaya_library.model.AppDatabase

val DB_NAME = "librarydb"

fun buildDB(context:Context):AppDatabase{
    val db = Room.databaseBuilder(context, AppDatabase::class.java, "librarydb")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)

        .build()
    return db
}


val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user" +
                " RENAME COLUMN first_name TO name")
        database.execSQL("INSERT INTO user(username,name,password) VALUES('tes123', 'tes', 123)")

    }


}


val MIGRATION_2_3 = object: Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todoo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
        database.execSQL("INSERT INTO user(username,name,password) VALUES('tes321', 'tes', 321)")

    }


}




