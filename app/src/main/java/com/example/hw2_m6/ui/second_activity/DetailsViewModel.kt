package com.example.hw2_m6.ui.second_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6.data.Repository
import com.example.hw2_m6.data.Resource
import com.example.hw2_m6.data.model.Character

class DetailsViewModel (private val repository: Repository) : ViewModel() {

    fun getData(id: Int): LiveData<Resource<Character>> = repository.getCharacter(id)

}