package com.example.receitasrapidas.DataBase.DBViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.receitasrapidas.DataBase.AppDataBase
import com.example.receitasrapidas.DataBase.ModelDB.FavRecipe
import com.example.receitasrapidas.DataBase.Repository.FavRecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbViewModel(application: Application): AndroidViewModel(application) {

    val allFavRecipes: LiveData<List<FavRecipe>>
    private val repository: FavRecipesRepository

    init {
        val favDao = AppDataBase.getDataBase(application).favDao()
        repository = FavRecipesRepository(favDao)
        allFavRecipes = repository.allFav
    }

    fun insert(recipe: FavRecipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavourite(recipe)
        }
    }

    fun deleteAll(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll(title)
        }
    }
}
