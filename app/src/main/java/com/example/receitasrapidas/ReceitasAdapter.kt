package com.example.receitasrapidas

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView

class ReceitasAdapter(val context: Context, val receitasList : MutableList<Receita>)
    : RecyclerView.Adapter<ReceitasAdapter.ReceitaViewHolder>() {

    var onClick : ((Receita) -> Unit)? = null

    class ReceitaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img : ImageView = view.findViewById(R.id.receitasImg)
        val title : TextView = view.findViewById(R.id.receitasTitle)
        val category : TextView = view.findViewById(R.id.category)
        val category2 : TextView = view.findViewById(R.id.category2)
        val layout : View? = view.findViewById(R.id.relative)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val itemList = LayoutInflater.from(context).inflate(R.layout.receitas, parent, false)
        return ReceitaViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
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
        holder.itemView.setOnClickListener {
            onClick?.invoke(receitasList[position])
        }
    }

    override fun getItemCount(): Int {
        return receitasList.size
    }
}