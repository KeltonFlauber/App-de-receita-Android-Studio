package com.example.receitasrapidas.Adapteers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.receitasrapidas.R
import com.example.receitasrapidas.Model.Receita

class ReceitasAdapter(private var receitasList: List<Receita>
, private val onClick : ((Receita) -> Unit))
    : RecyclerView.Adapter<ReceitasAdapter.ReceitaViewHolder>() {

    private var searchList = receitasList.toMutableList()

    class ReceitaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img : ImageView = view.findViewById(R.id.receitasImg)
        val title : TextView = view.findViewById(R.id.receitasTitle)
        val category : TextView = view.findViewById(R.id.category)
        val category2 : TextView = view.findViewById(R.id.category2)
        val layout : View? = view.findViewById(R.id.relative)

        fun onClicked(receita: Receita, onClick: (Receita) -> Unit){
            itemView.setOnClickListener {
                onClick(receita)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {
        val itemList = LayoutInflater.from(parent.context).inflate(R.layout.receitas, parent, false)
        return ReceitaViewHolder(itemList)
    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val mList = searchList[position]
        holder.onClicked(mList, onClick)
        holder.img.setImageResource(mList.img)
        holder.title.text = mList.title
        holder.category.text = mList.category
        if (holder.category.text.equals("")){
            holder.category.visibility = View.GONE
        }
        holder.category2.text = mList.category2
        if (holder.category2.text.equals("")){
            holder.category2.visibility = View.GONE
        }
        //holder.itemView.setOnClickListener {
           // onClick?.invoke(receitasList[position])
        //}
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    fun search(query: String): Boolean{

        searchList.clear()

        searchList.addAll(receitasList.filter { it.title.contains(query, true) })
        notifyDataSetChanged()

        return searchList.isEmpty()
    }

    fun clearSearchResult(){

        searchList = receitasList.toMutableList()
        notifyDataSetChanged()

    }
}