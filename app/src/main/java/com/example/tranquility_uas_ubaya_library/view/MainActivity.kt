package com.example.tranquility_uas_ubaya_library.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.model.AppDatabase
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserBook
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_1_2
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_2_3
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_3_4
import com.example.tranquility_uas_ubaya_library.util.MIGRATION_4_5
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get user session
        // SHARED KEY
        var SHARED_USERNAME = "usr"
        //

        val sharedPrefs = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        // retrieve user data
        var username = sharedPrefs.getString(SHARED_USERNAME, "") // default cond ""
//        // param di ambil dari shared preferences
//        // Retrieve data from SharedPreferences
//        var username = sharedPref?.getString("username", "")

        // ret global data

//        // Retrieve global data
//        val username = DataManager.getInstance().username

        if (username == ""){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        else{

//            Toast.makeText(this, username, Toast.LENGTH_LONG).show()
            // global data
//                // Set global data
            DataManager.getInstance().username = username!!



        }



        // date stuff

        // Get the current date
        // Get the current date
        val calendar: Calendar = Calendar.getInstance()
        val currentDate: Date = calendar.getTime()

        // Increment the date by 10 days

        // Increment the date by 10 days
        calendar.add(Calendar.DAY_OF_MONTH, 10)
        val futureDate: Date = calendar.getTime()

        // Format the dates using SimpleDateFormat

        // Format the dates using SimpleDateFormat
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val currentDateString: String = dateFormat.format(currentDate)
        val futureDateString: String = dateFormat.format(futureDate)




        //////


        drawerLayout = findViewById(R.id.drawerLayout)
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController

        NavigationUI.setupActionBarWithNavController(this,navController, drawerLayout)

        // for left drawer
        val navView = findViewById<NavigationView>(R.id.navView)
        NavigationUI.setupWithNavController(navView, navController)

//        navView.setOnClickListener()



        // for bottom nav
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setupWithNavController(navController)



        /// database
        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "librarydb")
            .build()
//        lifecycleScope.launch {
//            appDatabase.userDao().insertUser(
//                User(
//                    username = "tes123",
//                    password = "123",
//                    name = "alex")
//            )
//        }
//                lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Sang Pemimpi",
//                    author = "Andrea Hirata",
//                    ratings = 4.5,
//                    genre = "Roman",
//                    language = "Indonesia",
//                    release = "2006",
//                    photoUrl = "https://upload.wikimedia.org/wikipedia/id/8/89/Sang_Pemimpi_sampul.jpg",
//                    desc = "Buku Kedua Andrea Hirata ini bercerita tentang masa SMA tiga orang pemuda, yaitu Ikal, Arai dan Jimbron. Mereka bertiga adalah remaja yang berasal dari Belitong dan melanjutkan sekolah di Manggar, SMA Negeri pertama di Manggar. Untuk mencukupi kebutuhan sekolahnya Arai, Ikal dan Jimbron bekerja paruh waktu sebagai kuli di pasar ikan.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Tuhan Ada di Hatimu",
//                    author = "Husein Jafar Al-Hadar",
//                    ratings = 5.0,
//                    genre = "Motivasi",
//                    language = "Indonesia",
//                    release = "2020",
//                    photoUrl = "https://nourabooks.co.id/wp-content/uploads/2021/01/tuhan-ada-di-hatimu-3d-mockup.png",
//                    desc = "Buku Kedua Andrea Hirata ini bercerita tentang masa SMA tiga orang pemuda, yaitu Ikal, Arai dan Jimbron. Mereka bertiga adalah remaja yang berasal dari Belitong dan melanjutkan sekolah di Manggar, SMA Negeri pertama di Manggar. Untuk mencukupi kebutuhan sekolahnya Arai, Ikal dan Jimbron bekerja paruh waktu sebagai kuli di pasar ikan.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Seni Membaca Pikiran Orang Lain",
//                    author = "Euny Hong",
//                    ratings = 3.5,
//                    genre = "Pengembangan Diri",
//                    language = "Indonesia",
//                    release = "2010",
//                    photoUrl = "https://cdn.gramedia.com/uploads/items/9786020642581_NUNCHI_C_1_4-1.jpg",
//                    desc = "Buku yang bercerita tentang bagaimana cara memahami orang lain",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Emotional Intelligence",
//                    author = "Daniel Goleman",
//                    ratings = 4.5,
//                    genre = "Inteligensi Manusia",
//                    language = "Inggris",
//                    release = "1996",
//                    photoUrl = "https://opac.perpusnas.go.id/uploaded_files/sampul_koleksi/original/Monograf/sahat0256.jpg?rnd=1557519268",
//                    desc = "Kecerdasan emosional merupakan ciri orang-orang yang menonjol dalam kehidupan nyata: mereka yang memiliki hubungan dekat yang hangat dan menjadi bintang di tempat kerja. Ini juga ciri utama karakter dan disiplin diri, altruisme, serta belas kasih—kemampuan-kemampuan dasar yang dibutuhkan bila kita mengharapkan terciptanya masyarakat yang sejahtera.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Going Offline",
//                    author = "Desi Anwar",
//                    ratings = 4.5,
//                    genre = "Kemanusiaan",
//                    language = "Indonesia",
//                    release = "2020",
//                    photoUrl = "https://books.google.co.id/books/publisher/content?id=g7zNDwAAQBAJ&hl=id&pg=PP1&img=1&zoom=3&bul=1&sig=ACfU3U1Lpko6VDLSAkc-9VD9BfB8ul3ZhA&w=1280",
//                    desc = "Melalui media sosial dan realitas virtual, kehidupan online membuat kita terus terhubung dan selalu aktif. Ponsel cerdas kita adalah benda pertama yang kita raih saat bangun tidur dan yang terakhir kita letakkan sebelum tidur. Layar kecil di tangan kita itu memberikan kenyamanan, persahabatan, dan rangsangan yang membuat kita terus-menerus tertarik dan bersemangat serta mengalihkan perhatian kita dari dunia nyata. Bersamanya, kita jarang merasa bosan atau punya waktu untuk hanya duduk diam dan melamun.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "The Things You Can See Only When You Slow Down",
//                    author = "Haemin Sunim",
//                    ratings = 4.1,
//                    genre = "Motivasi",
//                    language = "Indonesia",
//                    release = "2012",
//                    photoUrl = "https://pembangunansosial.fisipol.ugm.ac.id/wp-content/uploads/sites/1145/2021/12/unnamed.png",
//                    desc = "Contohnya pada salah satu cerita, di mana Haemin Sunim sangat antusias ketika mendapatkan pekerjaan pertamanya sebagai seorang tenaga pengajar di salah satu universitas, dia sangat menggebu-gebu dan bersemangat.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Cinta untuk Perempuan yang Tidak Sempurna",
//                    author = "Najelaa Shihab",
//                    ratings = 3.8,
//                    genre = "Roman",
//                    language = "Indonesia",
//                    release = "2020",
//                    photoUrl = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1620793656i/55157683.jpg",
//                    desc = "Persoalan bekerja atau tidak bekerja bagi perempuan yang sudah menikah dan memiliki anak memang selalu menghadirkan berbagai dilema. Saat membuat sebuah pilihan pun, seorang perempuan masih akan dibayangi perasaan bersalah. Ketika memilih untuk tetap bekerja, ada rasa bersalah karena tidak memberi cukup waktu untuk keluarga. Namun, ketika memilih untuk tidak bekerja, ada rasa bersalah karena tidak memanfaatkan potensi diri yang ada dengan lebih maksimal.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Mengheningkan Cinta",
//                    author = "Adjie Santosoputro",
//                    ratings = 4.3,
//                    genre = "Roman",
//                    language = "Indonesia",
//                    release = "2020",
//                    photoUrl = "https://books.google.co.id/books/publisher/content?id=iqfSDwAAQBAJ&hl=id&pg=PP1&img=1&zoom=3&bul=1&sig=ACfU3U0pRqhzvNvSLnNFaqBN4WKha-9N7w&w=1280",
//                    desc = "Mengheningkan Cinta merupakan buku yang sangat ramah untuk jiwa. Tidak ada kesan menghakimi atau menggurui perihal cinta dalam buku ini. Justru kita akan merasa seolah tangan kita digandeng dan tubuh kita didekap melalui berbagai pemahaman dan pemaknaan soal cinta. Cinta mungkin tak selalu membuat kita baik-baik saja. Kadang ada luka dan rasa sakit yang ditimbulkannya. Namun, kita selalu punya kemampuan untuk menyeimbangkan rasa dan menjalani hidup bersama cinta.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Loving the Wounded Soul",
//                    author = "Regis Machdy",
//                    ratings = 4.5,
//                    genre = "Motivasi",
//                    language = "Indonesia",
//                    release = "2019",
//                    photoUrl = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1569808196l/50029689.jpg",
//                    desc = "Loving the Wounded Soul merupakan buku yang sangat penting untuk dibaca kita semua. Siapa saja bisa mengalami depresi, meski memang ada orang-orang tertentu yang memiliki tingkat kerentanan berbeda terhadap depresi. Dari buku ini, kita bisa memahami kesehatan mental dengan lebih mendalam melalui bahasa yang sangat mudah dicerna. Ditambah dengan pengalaman-pengalaman Regis sebagai penyintas depresi dan akademisi psikologi, kita mendapat banyak pandangan baru tentang depresi dan pentingnya mencintai diri sendiri.",
//                    stock = 50
//                )
//            )
//        }
//        lifecycleScope.launch {
//            appDatabase.userDao().insertBook(
//                Book(
//                    name = "Mariposa",
//                    author = "Hadayatul Fajriyah",
//                    ratings = 5.0,
//                    genre = "Roman",
//                    language = "Indonesia",
//                    release = "2018",
//                    photoUrl = "https://upload.wikimedia.org/wikipedia/id/b/b0/Mariposa_%28sampul%29.jpeg",
//                    desc = "Mariposa adalah sebuah novel komedi remaja Indonesia karya Luluk HF, mahasiswa Universitas Muhammadiyah Malang (UMM). Novel tersebut terjual sebanyak 17.849 eksemplar dan diadaptasi ke dalam sebuah film oleh Fajar Bustomi",
//                    stock = 50
//                )
//            )
//        }




    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || navController.navigateUp()

    }
}