package com.example.tranquility_uas_ubaya_library.util


import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tranquility_uas_ubaya_library.model.AppDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

val DB_NAME = "librarydb"

fun buildDB(context:Context):AppDatabase{
    val db = Room.databaseBuilder(context, AppDatabase::class.java, "librarydb")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)

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

val MIGRATION_3_4 = object: Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todoo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
        database.execSQL("INSERT INTO book(name,author,ratings,genre,language,release,photoUrl,desc,stock) VALUES('This Book Loves You', 'Felix Arvid', 3.8, 'Humor', 'Swedish', '2015', 'https://m.media-amazon.com/images/I/71vmpcJWq7L._AC_UF1000,1000_QL80_.jpg','Den här boken älskar dig (på engelska: This Book Loves You) är en bok från 2015 av svenska youtubern Felix PewDiePie Kjellberg. Boken är en parodi på självhjälpsböcker med inspirerande citat',100)")

    }


}

fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url).resize(400, 400).centerCrop()
        .into(this, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
//                TODO0("Not yet implemented")
            }

        })

}

// custom binding adapter
@BindingAdapter("android:imageUrl")
fun loadPhotoURL(view: ImageView, url:String?){
    view.loadImage(url)
}




