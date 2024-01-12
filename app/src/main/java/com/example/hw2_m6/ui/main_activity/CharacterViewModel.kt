package com.example.hw2_m6.ui.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hw2_m6.data.Repository
import com.example.hw2_m6.data.Resource
import com.example.hw2_m6.data.model.Character

class CharacterViewModel(private val repository: Repository) : ViewModel() {

    fun getCharacters(): LiveData<Resource<List<Character>>> = repository.getCharacters()

}