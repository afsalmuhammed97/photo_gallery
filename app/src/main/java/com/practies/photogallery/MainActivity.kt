package com.practies.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.practies.photogallery.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        // val navController=navHostFragment.navController


    }
}

//val navHostFragment = supportFragmentManager
//    .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
//val navController = navHostFragment.navController
