package com.example.receitasrapidas.fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.receitasrapidas.BolosETortasDoces
import com.example.receitasrapidas.NaviDraw
import com.example.receitasrapidas.R
import com.example.receitasrapidas.Receita
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
        val intent = Intent(this@MainScreen.requireContext(), BolosETortasDoces::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "bolosEtortasDoces")
        startActivity(intent)
    }

    private fun onMassasClick(){
        val intent = Intent(this@MainScreen.requireContext(), BolosETortasDoces::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "massas")
        startActivity(intent)
    }

    private fun onSalgadosClick(){
        val intent = Intent(this@MainScreen.requireContext(), BolosETortasDoces::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "salgados")
        startActivity(intent)
    }

    private fun onSobremesasClick(){
        val intent = Intent(this@MainScreen.requireContext(), BolosETortasDoces::class.java)
        showProgressBar(true)
        intent.putExtra("intentBooks", "sobremesas")
        startActivity(intent)
    }

    private fun showProgressBar(tOUf: Boolean){
        binding.progressBar.isVisible = tOUf
    }

}