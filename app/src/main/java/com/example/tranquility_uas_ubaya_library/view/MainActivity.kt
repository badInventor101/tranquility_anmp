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
//                    name = "alex"
//
//
//
//                )
//            )



//        }




    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout) || navController.navigateUp()

    }
}