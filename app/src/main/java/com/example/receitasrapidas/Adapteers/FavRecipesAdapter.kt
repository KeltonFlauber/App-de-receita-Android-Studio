package com.example.receitasrapidas.Adapteers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe
import com.example.receitasrapidas.Model.Receita
import com.example.receitasrapidas.R

class FavRecipesAdapter(val context: Context, val onItemClicked: (Receita) -> Unit)
    : RecyclerView.Adapter<FavRecipesAdapter.ReceitaViewHolder>() {

    private var receitasList = emptyList<Receita>()

    class ReceitaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img : ImageView = view.findViewById(R.id.receitasImg)
        val title : TextView = view.findViewById(R.id.receitasTitle)
        val category : TextView = view.findViewById(R.id.category)
        val category2 : TextView = view.findViewById(R.id.category2)
        val layout : View? = view.findViewById(R.id.relative)

        fun onclicked(item: Receita, onItemClicked: (Receita) -> Unit){

            itemView.setOnClickListener {

                onItemClicked(item)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRecipesAdapter.ReceitaViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.receitas, parent, false)
        return ReceitaViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {

        holder.onclicked(receitasList[position], onItemClicked)
        holder.img.setImageResource(receitasList[position].img)
        holder.title.text = receitasList[position].title
        holder.category.text = receitasList[position].category
        if (holder.category.text.equals("")){
            holder.category.visibility = View.GONE
        }
        holder.category2.text = receitasList[position].category2
        if (holder.category2.text.equals("")){
            holder.category2.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return receitasList.size
    }

    fun setData(newList: List<Receita>){

        receitasList = newList
        notifyDataSetChanged()

    }

}