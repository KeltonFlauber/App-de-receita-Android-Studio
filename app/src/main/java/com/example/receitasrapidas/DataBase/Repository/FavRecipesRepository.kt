package com.example.receitasrapidas.DataBase.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.receitasrapidas.DataBase.Dao.RecipesDao
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe
import com.example.receitasrapidas.Model.Receita

class FavRecipesRepository(private val myDao: RecipesDao) {

    val allFav: LiveData<List<FavRecipe>> = myDao.getAllFav()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun setFavourite(recipes: FavRecipe){

        myDao.setFavourite(recipes)

    }

    suspend fun deleteAll(title: String){

        myDao.deleteAll(title)

    }
}