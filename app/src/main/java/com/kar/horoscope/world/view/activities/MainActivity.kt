package com.kar.horoscope.world.view.activities

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.kar.horoscope.world.R
import com.kar.horoscope.world.repository.MainRepository
import com.kar.horoscope.world.repository.adapter.MainAdapter
import com.kar.horoscope.world.viewmodels.main.MainViewModel
import com.kar.horoscope.world.viewmodels.main.ViewModelFactory
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)


        ///Set Navigation Header parameters
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val view = navigationView.getHeaderView(0)

        val userImage = view.findViewById<CircleImageView>(R.id.userProfileImage)
        val userName = view.findViewById<TextView>(R.id.userName)
        val userEmail = view.findViewById<TextView>(R.id.userEmail)

        val user = FirebaseAuth.getInstance().currentUser
        Glide.with( userImage.context ).load(user?.photoUrl).into(userImage)
        userEmail.text = user?.email
        userName.text = user?.displayName

        val repository = MainRepository( this )
        val viewModel: MainViewModel by lazy {
            ViewModelProviders.of(this,
                ViewModelFactory(repository)
            ).get(MainViewModel::class.java)
        }

        viewModel.getObservableListModel.subscribe{
            val adapter = MainAdapter( it, this )
            recyclerView.layoutManager = GridLayoutManager(this, 3)
            recyclerView.adapter = adapter
        }

        navigationView.setNavigationItemSelectedListener {
            val id = it.itemId
            viewModel.navigationLogic( id )
            true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( toggle.onOptionsItemSelected(item) )
            return true
        return super.onOptionsItemSelected(item)
    }
}
