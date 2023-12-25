package com.example.hw2_m6.ui.main_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_m6.databinding.ActivityCharacterBinding
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.ui.main_activity.adpter.CharacterAdapter
import com.example.hw2_m6.ui.second_activity.DetailsActivity
import com.example.hw2_m6.ui.utils.CharacterKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterBinding
    private lateinit var viewModel: CharacterViewModel
   // private val characterAdapter by lazy { CharacterAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
       // setupCharactersRecycler()
        viewModel.getAllData().observe(this) {
             val adapter = CharacterAdapter(this::onClickItem, it )
             binding.rv.adapter = adapter
            //characterAdapter.submitList(it)
        }

    }

  /*  private fun setupCharactersRecycler() = with(binding.rv) {
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(
            this@CharacterActivity,
            LinearLayoutManager.VERTICAL,
            false

        )
    }*/

    private fun onClickItem(character: Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CharacterKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }


}