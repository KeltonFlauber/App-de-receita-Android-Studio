package com.example.receitasrapidas.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.receitasrapidas.UI.RecipeSelection
import com.example.receitasrapidas.databinding.FragMainBinding

class MainScreen: Fragment() {

    private lateinit var binding : FragMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragMainBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        showProgressBar(false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cakeBook.setOnClickListener{ onBolosClick() }
        binding.massasBook.setOnClickListener{ onMassasClick() }
        binding.salBook.setOnClickListener { onSalgadosClick() }
        binding.sobremesaBook.setOnClickListener { onSobremesasClick() }
    }

    private fun onBolosClick(){
        val intent = Intent(this@MainScreen.requireContext(), RecipeSelection::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "bolosEtortasDoces")
        startActivity(intent)
    }

    private fun onMassasClick(){
        val intent = Intent(this@MainScreen.requireContext(), RecipeSelection::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "massas")
        startActivity(intent)
    }

    private fun onSalgadosClick(){
        val intent = Intent(this@MainScreen.requireContext(), RecipeSelection::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "salgados")
        startActivity(intent)
    }

    private fun onSobremesasClick(){
        val intent = Intent(this@MainScreen.requireContext(), RecipeSelection::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "sobremesas")
        startActivity(intent)
    }

    private fun showProgressBar(tOUf: Boolean){
        binding.progressBar.isVisible = tOUf
    }

}