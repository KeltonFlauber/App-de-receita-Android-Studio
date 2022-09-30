package com.example.receitasrapidas

import android.content.Intent
import android.content.res.Configuration
import android.media.VolumeShaper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.receitasrapidas.databinding.ActivityBolosEtortasDocesBinding

class BolosETortasDoces : AppCompatActivity() {

    private lateinit var binding : ActivityBolosEtortasDocesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bolos_etortas_doces)

        //Lista de Receitas

        val categoria1 = intent.extras?.get("intentBooks")
        val backButtonIntent = intent.extras?.get("intentBack")


        val receitasList : MutableList<Receita>  = mutableListOf()

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
        val adapter = ReceitasAdapter(this, receitasList)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        adapter.onClick = {
            val intent = Intent(this, Passo_a_passo_receita::class.java)
            intent.putExtra("receita", it)
            startActivity(intent)
        }

        binding.imgbBtnBack.setOnClickListener{
            val intent = Intent(this, NaviDraw::class.java)
            startActivity(intent)
            finish()
        }
    }
}