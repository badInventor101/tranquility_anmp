package com.example.tranquility_uas_ubaya_library.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceDataStore
import android.provider.Settings.Global.putString
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.tranquility_uas_ubaya_library.DataManager
import com.example.tranquility_uas_ubaya_library.R
import com.example.tranquility_uas_ubaya_library.databinding.ActivityLoginBinding
import com.example.tranquility_uas_ubaya_library.model.AppDatabase
import com.example.tranquility_uas_ubaya_library.model.Book
import com.example.tranquility_uas_ubaya_library.model.User
import com.example.tranquility_uas_ubaya_library.model.UserDao
import com.example.tranquility_uas_ubaya_library.util.*
import com.example.tranquility_uas_ubaya_library.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), LoginUserInterface {
    private lateinit var binding: ActivityLoginBinding // layoutnya
    private lateinit var viewModel: LoginViewModel
    private lateinit var userDao: UserDao


    var log_count = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
//        setContentView(R.layout.activity_login)
        setContentView(view)

        // untuk button regis
        binding.btnReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



//        binding.btnLogin.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this, "binding.btnLogin.text.toString()", Toast.LENGTH_LONG).show()
//
//        })




//        val appDat = buildDB(this)
//        userDao = appDat.userDao()

        /// database
        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "librarydb"
        ).build()

//        lifecycleScope.launch {
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
        ///////
//        userDao = appDatabase.userDao()
//
//        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //// BARU
        binding.loginListener = this // dari Interfaces.kt ( merah trus implement di bawah (onUserLoginClick) )




    }

    fun observeUserViewModel(){
        viewModel.userLD.observe(this, Observer {
//            Log.d("AAAAA", it.toString())

            if (it?.username != null){
                Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
                Toast.makeText(this, "suceess", Toast.LENGTH_LONG).show()
                Log.d("AAAAA", "my Message")
            }
            else{
                Toast.makeText(this, "salah", Toast.LENGTH_LONG).show()
                Log.d("AAAAA", "my Message")

            }
//            viewModel.userLD.postValue(null) // untuk clear setelah di observe harus null, karena login hanya TIDAK AND YA

        })
    }

    override fun onUserLoginClick(v: View) {   // dari Interfaces.kt
        viewModel.login(binding.txtInputUsername.text.toString(), binding.txtInputPass.text.toString())     // viewmodel call ke DAO
        if (log_count == 0){
            Toast.makeText(this, "Tekan login lagi jika yakin login", Toast.LENGTH_LONG).show()
            log_count++


        }
        else{
            Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
            if (viewModel.userLD.value.toString() == "null"){
                Toast.makeText(this, "Mohon cek username dan password anda", Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(this, "Success Login", Toast.LENGTH_LONG).show()

                // SHARED KEY
                var SHARED_USERNAME = "usr"
                //
                val sharedPrefs = getSharedPreferences(packageName, Context.MODE_PRIVATE)
                val editor = sharedPrefs.edit{
                    putString(SHARED_USERNAME, binding.txtInputUsername.text.toString())


                }
                ///

//                // global data
//                // Set global data
//                DataManager.getInstance().username = binding.txtInputUsername.text.toString()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }
            log_count = 0

        }
//        observeUserViewModel()

//        viewModel.getAll()    // viewmodel call ke DAO
//        Toast.makeText(v.context, binding.txtInputUsername.text, Toast.LENGTH_LONG).show()
//        Toast.makeText(this, "binding.btnLogin.text.toString()", Toast.LENGTH_LONG).show()

//        Log.d("AAAAA", "my Message")

//        observeUserViewModel()

//        Toast.makeText(this, viewModel.userLD.value.toString(), Toast.LENGTH_LONG).show()
//        viewModel.userLD.postValue(null) // untuk clear setelah di observe harus null, karena login hanya TIDAK AND YA

    }

//    fun login() {
//        val username = viewModel.username
//        val password = viewModel.password
//
//        // Observe LiveData version
//        userDao.getUserByUsernameLiveData(username, password).observe(this, { user ->
//            if (user != null && user.password == password) {
//                // Login successful, do something
//                Toast.makeText(this, "sucess", Toast.LENGTH_LONG).show()
//
//            } else {
//                // Invalid username or password, handle error
//                Toast.makeText(this, "wrong", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}