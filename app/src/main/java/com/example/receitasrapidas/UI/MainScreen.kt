package com.example.receitasrapidas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.receitasrapidas.databinding.ActivityTelaPrincipalBinding

class MainScreen : AppCompatActivity() {

    private lateinit var binding : ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navFragment = (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id)) as NavHostFragment
        val navController = navFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }
}