package com.example.receitasrapidas.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.receitasrapidas.Passo_a_passo_receita
import com.example.receitasrapidas.R
import com.example.receitasrapidas.Receita
import com.example.receitasrapidas.ReceitasAdapter
import com.example.receitasrapidas.databinding.FavoritesFragBinding
import com.google.firebase.firestore.FirebaseFirestore

class Favorites : Fragment() {

    private lateinit var binding: FavoritesFragBinding
    private val dataBase = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesFragBinding.inflate(inflater)

        //Recycler setup\\
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerFavs.layoutManager = GridLayoutManager(activity, 4)
        } else {
            binding.recyclerFavs.layoutManager = GridLayoutManager(activity, 2)
        }
        binding.recyclerFavs.setHasFixedSize(true)
        //END\\

        //listSet

        val bundle = arguments

        /*dataBase.collection("Users").document("Favorites")
            .addSnapshotListener { document, error ->
                if (document != null) {
                    val imgSet = document.getLong("imgSet")
                    val title = document.getString("titleSet")
                    val ingredientes = document.getString("ingredientes")
                    val preparo = document.getString("preparo")
                    val category1Set = document.getString("category1Set")
                    val category2set = document.getString("category2set")

                    val receitasList: MutableList<Receita> = mutableListOf()
                    receitasList.add(
                        Receita(
                            document.getLong("imgSet") as Int, title.toString(),
                            ingredientes.toString(), preparo.toString(),
                            category1Set.toString(), category2set.toString()
                        )
                    )
                    val adapter = ReceitasAdapter(requireActivity(), receitasList)
                    binding.recyclerFavs.adapter = adapter
                    adapter.onClick = {
                        val intent = Intent(this@Favorites.requireContext(), Passo_a_passo_receita::class.java)
                        intent.putExtra("receita", it)
                        startActivity(intent)
                    }
                }
            }*/


        return binding.root
    }
}