package com.example.receitasrapidas.DataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe
import com.example.receitasrapidas.Model.Receita


@Dao
interface RecipesDao {

    @Query("SELECT * FROM all_favourites")
    fun getAllFav(): LiveData<List<FavRecipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavourite(recipes: FavRecipe)

    @Query("DELETE FROM all_favourites WHERE titleFav = :title")
    suspend fun deleteAll(title: String)

}