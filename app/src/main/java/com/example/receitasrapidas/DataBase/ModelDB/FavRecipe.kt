package com.example.receitasrapidas.DataBase.ModelDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.receitasrapidas.Model.Receita


@Entity(tableName = "all_favourites")
data class FavRecipe(

    @ColumnInfo(name = "imageFav") val img : Int,
    @ColumnInfo(name = "titleFav") val title : String,
    @ColumnInfo(name = "ingredientesFav") val ingredientes : String,
    @ColumnInfo(name = "preparoFav") val preparo : String,
    @ColumnInfo(name = "category1Fav") val category : String,
    @ColumnInfo(name = "category2Fav") val category2 : String

){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

fun FavRecipe.toReceita(): Receita{

    return Receita(
        img = this.img,
        title = this.title,
        ingredientes = this.ingredientes,
        preparo = this.preparo,
        category = this.category,
        category2 = this.category2
    )

}