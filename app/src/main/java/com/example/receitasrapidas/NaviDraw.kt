package com.example.receitasrapidas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.receitasrapidas.databinding.ActivityNaviDrawBinding
import com.example.receitasrapidas.databinding.ActivityTelaPrincipalBinding
import com.example.receitasrapidas.fragments.MainScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.firestore.FirebaseFirestore

class NaviDraw : AppCompatActivity() {

    lateinit var binding : ActivityNaviDrawBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navi_draw)

        val navFragment = (supportFragmentManager.findFragmentById(binding.fragmentContainerView.id)) as NavHostFragment
        val navController = navFragment.navController
        binding.navView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.navView.menu.findItem(R.id.btnLogOut).setOnMenuItemClickListener { menuItem : MenuItem? ->

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            true }
        //Receitas Codes
        getData()

    }

    private fun getData(){
        val headerView : View = binding.navView.getHeaderView(0)
        val textHeader = headerView.findViewById<TextView>(R.id.userName)
        FirebaseFirestore.getInstance().collection("Users").document("UserName")
            .addSnapshotListener{document, error ->
                if(document!=null){
                    textHeader.text = document.getString("userName")
                }
            }
    }

}