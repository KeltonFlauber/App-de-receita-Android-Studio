package com.example.receitasrapidas.fragments

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.receitasrapidas.Adapteers.FavRecipesAdapter
import com.example.receitasrapidas.Adapteers.ReceitasAdapter
import com.example.receitasrapidas.DataBase.DBViewModel.DbViewModel
import com.example.receitasrapidas.DataBase.ModelDB.toReceita
import com.example.receitasrapidas.Model.Receita
import com.example.receitasrapidas.Passo_a_passo_receita
import com.example.receitasrapidas.databinding.FavoritesFragBinding
import com.google.firebase.firestore.FirebaseFirestore

class Favorites : Fragment() {

    private lateinit var binding: FavoritesFragBinding
    private lateinit var recipeList: MutableList<Receita>
    private val dataBase = FirebaseFirestore.getInstance()
    private val adapter by lazy { ReceitasAdapter(recipeList){

        val intent = Intent(this@Favorites.requireContext(), Passo_a_passo_receita::class.java)
        intent.putExtra("receita", it)
        startActivity(intent)

    } }

    private val adapterDb by lazy { FavRecipesAdapter(requireContext()){

        val intent = Intent(this@Favorites.requireContext(), Passo_a_passo_receita::class.java)
        intent.putExtra("receita", it)
        startActivity(intent)

    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeList = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavoritesFragBinding.inflate(inflater, container, false)

        //listSet

        //val bundle = arguments

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

        val viewModel: DbViewModel =
            ViewModelProvider(this).get(DbViewModel::class.java)

        viewModel.allFavRecipes.observe(viewLifecycleOwner, Observer {

            for ((position) in it.withIndex()){
                recipeList.add(it[position].toReceita())
            }
            adapterDb.setData(recipeList)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Recycler setup\\
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.recyclerFavs.layoutManager = GridLayoutManager(activity, 4)
        } else {
            binding.recyclerFavs.layoutManager = GridLayoutManager(activity, 2)
        }
        binding.recyclerFavs.adapter = adapterDb
        binding.recyclerFavs.setHasFixedSize(true)
        //END\\

    }
}