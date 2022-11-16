package com.example.receitasrapidas.UI

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.receitasrapidas.Adapteers.ReceitasAdapter
import com.example.receitasrapidas.Model.Receita
import com.example.receitasrapidas.Passo_a_passo_receita
import com.example.receitasrapidas.R
import com.example.receitasrapidas.databinding.ActivityBolosEtortasDocesBinding
import com.ferfalk.simplesearchview.SimpleSearchView

class RecipeSelection : AppCompatActivity() {

    private lateinit var binding : ActivityBolosEtortasDocesBinding
    private lateinit var receitasList : MutableList<Receita>
    val adapter by lazy { ReceitasAdapter(receitasList){

        val intent = Intent(this, Passo_a_passo_receita::class.java)
        intent.putExtra("receita", it)
        startActivity(intent)

    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bolos_etortas_doces)

        initSearch()

        //Lista de Receitas

        val categoria1 = intent.extras?.get("intentBooks")
        val backButtonIntent = intent.extras?.get("intentBack")


        receitasList = mutableListOf()

        if(categoria1 == "bolosEtortasDoces" || backButtonIntent == "bolosEtortasDoces") {
            receitasList.add(
                Receita(
                    R.drawable.bolodemilho, getString(R.string.boloMilho),
                    getString(R.string.bolo_milho_cremoso_ingredientes),
                    getString(R.string.bolo_milho_cremoso_preparo),
                    getString(R.string.bolos), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_torta_leite_condensado, getString(R.string.nome_torta_docedeleite),
                    getString(R.string.torta_doce_de_leite_ingredienteas),
                    getString(R.string.torta_doce_de_leite_preparo),
                    getString(R.string.tortas), getString(R.string.doces)
                )
            )
        }else if(categoria1 == "massas" || backButtonIntent == "massas"){
            receitasList.add(
                Receita(
                    R.drawable.receitamacarrao, getString(R.string.macarrão),
                    getString(R.string.macarrão_de_forno_ingredientes),
                    getString(R.string.macarrão_de_forno_preparo),
                    getString(R.string.massas), ""
                )
            )
        }else if (categoria1 == "salgados" || backButtonIntent == "salgados"){
            receitasList.add(
                Receita(
                    R.drawable.receita_coxinha_de_frango, getString(R.string.coxinha_de_frango),
                    getString(R.string.coxinha_de_frango_ingredientes),
                    getString(R.string.coxinha_de_frango_preparo),
                    getString(R.string.salgadod_category), ""
                )
            )
        }else if (categoria1 == "sobremesas" || backButtonIntent == "sobremesas"){
            receitasList.add(
                Receita(
                    R.drawable.receita_abacaxi_com_creme_de_leite_condensado, getString(R.string.abaxaxi_com_CREME_DE_LEITE_CONDENSADO),
                    getString(R.string.abaxaxi_com_CREME_DE_LEITE_CONDENSADO_ingredientes),
                    getString(R.string.abaxaxi_com_CREME_DE_LEITE_CONDENSADO_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_cheesecake_de_morango_no_pote, getString(R.string.CHEESECAKE_DE_MORANGO_NO_POTE),
                    getString(R.string.CHEESECAKE_DE_MORANGO_NO_POTE_ingredientes),
                    getString(R.string.CHEESECAKE_DE_MORANGO_NO_POTE_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_pudim_de_leite, getString(R.string.PUDIM_DE_LEITE_EM_PÓ),
                    getString(R.string.PUDIM_DE_LEITE_EM_PÓ_ingredinetes),
                    getString(R.string.PUDIM_DE_LEITE_EM_PÓ_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_mousse_de_maracuja, getString(R.string.MOUSSE_DE_MARACUJÁ),
                    getString(R.string.MOUSSE_DE_MARACUJÁ_ingredientes),
                    getString(R.string.MOUSSE_DE_MARACUJÁ_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_sobremesa_gelada_de_chocolate, getString(R.string.SOBREMESA_GELADA_DE_CHOCOLATE),
                    getString(R.string.SOBREMESA_GELADA_DE_CHOCOLATE_ingredientes),
                    getString(R.string.SOBREMESA_GELADA_DE_CHOCOLATE_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
            receitasList.add(
                Receita(
                    R.drawable.receita_pudim_de_leite_condensado, getString(R.string.PUDIM_DE_LEITE_CONDENSADO),
                    getString(R.string.PUDIM_DE_LEITE_CONDENSADO_ingredientes),
                    getString(R.string.PUDIM_DE_LEITE_CONDENSADO_preparo),
                    getString(R.string.sobremesas), getString(R.string.doces)
                )
            )
        }

        //Recycler configurations
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        }else{
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        //adapter.setData(receitasList)
        //adapter.onClick = {
            //val intent = Intent(this, Passo_a_passo_receita::class.java)
            //intent.putExtra("receita", it)
            //startActivity(intent)
        //}

        binding.imgbBtnBack.setOnClickListener{
            val intent = Intent(this, NaviDraw::class.java)
            startActivity(intent)
            finish()
        }

        binding.imgbBtnSearch.setOnClickListener {
            binding.searchView.showSearch()
        }
    }

    fun initSearch(){

        binding.searchView.setOnQueryTextListener(object : SimpleSearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {

                binding.searchResult.text = if (adapter.search(newText)){
                    "Not Found!"
                }else{
                    ""
                }

                return true
            }

            override fun onQueryTextCleared(): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })

        binding.searchView.setOnSearchViewListener(object : SimpleSearchView.SearchViewListener{
            override fun onSearchViewClosed() {

                adapter.clearSearchResult()

            }

            override fun onSearchViewClosedAnimation() {

            }

            override fun onSearchViewShown() {

            }

            override fun onSearchViewShownAnimation() {

            }
        })
    }
}