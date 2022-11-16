package com.example.receitasrapidas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.receitasrapidas.DataBase.DBViewModel.DbViewModel
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe
import com.example.receitasrapidas.Model.Receita
import com.example.receitasrapidas.R
import com.example.receitasrapidas.databinding.ActivityPassoApassoReceitaBinding
import com.example.receitasrapidas.fragments.Favorites
import com.google.firebase.firestore.FirebaseFirestore

class Recipes_Details : AppCompatActivity() {

    lateinit var binding: ActivityPassoApassoReceitaBinding
    lateinit var viewModel: DbViewModel
    private val dataBase = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_passo_apasso_receita)


        viewModel = ViewModelProvider(this).get(DbViewModel::class.java)

        //extras input
        val intentExtras = intent.getParcelableExtra<Receita>("receita")
        binding.imgReceita.setImageResource(intentExtras!!.img)
        binding.receitasTitle.text = intentExtras.title
        binding.ingredientesTxt.text = intentExtras.ingredientes
        binding.modoPreparoTexto.text = intentExtras.preparo

        val favList = FavRecipe(
            intentExtras.img, intentExtras.title, intentExtras.ingredientes,
            intentExtras.preparo, intentExtras.category, intentExtras.category2)

        //favCheck
        viewModel.allFavRecipes.observe(this, Observer {

            for ((position) in it.withIndex()){
                if (it[position].title == intentExtras.title){
                    binding.toggleButton.isChecked = true
                    binding.toggleButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                }
            }
        })

        binding.toggleButton.setOnClickListener{
            if (binding.toggleButton.isChecked){
                binding.toggleButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(this, "ADD to Favorites", Toast.LENGTH_SHORT).show()
                viewModel.insert(favList)
                //addToRoom()
            }else{
                binding.toggleButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.deleteAll(favList.title)
                Toast.makeText(this, "Remove from Favorites", Toast.LENGTH_SHORT).show()
            }
        }

        //tollbar setting
        binding.imgBtnBack.setOnClickListener{
            /*val intent = Intent(this, RecipeSelection::class.java)
            if(intentExtras.category == getString(R.string.bolos) ||
                intentExtras.category2 == getString(R.string.doces)){
                intent.putExtra("intentBack", "bolosEtortasDoces")
            }else if(intentExtras.category == getString(R.string.massas)){
                intent.putExtra("intentBack", "massas")
            }else if(intentExtras.category == getString(R.string.salgadod_category)){
                intent.putExtra("intentBack", "salgados")
            }else if(intentExtras.category == getString(R.string.sobremesas_category)){
                intent.putExtra("intentBack", "sobremesas")
            }
            startActivity(intent)*/
            finish()
        }
    }

    private fun dataBaseFavsAdd(){
        val intentExtras = intent.getParcelableExtra<Receita>("receita")
        val hashMap = hashMapOf(
            "imgSet" to intentExtras!!.img,
            "titleSet" to intentExtras.title,
            "ingredientes" to intentExtras.ingredientes,
            "preparo" to intentExtras.preparo,
            "category1Set" to intentExtras.category,
            "category2set" to intentExtras.category2
        )
        dataBase.collection("Users").document("Favorites").set(hashMap)
            .addOnCompleteListener{
                val toast = Toast.makeText(this, "Adicionado aos favoritos", Toast.LENGTH_SHORT)
                toast.show()
            }


        dataBase.collection("Users").document("Favorites")
            .addSnapshotListener{ document, error ->
                val imgSet = document?.getLong("imgSet")
                val title = document?.getString("titleSet")
                val ingredientes = document?.getString("ingredientes")
                val preparo = document?.getString("preparo")
                val category1Set = document?.getString("category1Set")
                val category2set = document?.getString("category2set")

                val bundle = bundleOf(
                    "imgSet" to imgSet,
                    "titleSet" to title,
                    "ingredientes" to ingredientes,
                    "preparo" to preparo,
                    "category1Set" to category1Set,
                    "category2set" to category2set
                )

                val fragment = Favorites()
                fragment.arguments = bundle
            }
    }

    fun addToRoom(){

        val intentExtras = intent.getParcelableExtra<Receita>("receita")

        val favList = FavRecipe(intentExtras!!.img, intentExtras.title, intentExtras.ingredientes,
            intentExtras.preparo, intentExtras.category, intentExtras.category2)

        if (viewModel.allFavRecipes.hasObservers()){

            viewModel.allFavRecipes.observe(this, Observer {

                for ((position) in it.withIndex()){
                    if (it[position].title != intentExtras.title){
                        Toast.makeText(this, "ADD to Favorites", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Can´t ADD to Favorites", Toast.LENGTH_SHORT).show()
                    }
                }
            })

        }else {
            viewModel.insert(favList)
            Toast.makeText(this, "ADD to Favorites", Toast.LENGTH_SHORT).show()
        }

        //favList.add(FavRecipe(
            //intentExtras!!.img, intentExtras.title, intentExtras.ingredientes,
            //intentExtras.preparo, intentExtras.category, intentExtras.category2))
    }
}