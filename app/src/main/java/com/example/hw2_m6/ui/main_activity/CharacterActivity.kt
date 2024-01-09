package com.example.hw2_m6.ui.main_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw2_m6.data.Resource
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
    private val characterAdapter by lazy { CharacterAdapter(this::onClickItem) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

        viewModel.getCharacters().observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    characterAdapter.submitList(it.data)
                }
            }
        }
        setupCharactersRecycler()
    }

    private fun setupCharactersRecycler() /*= with(binding.rv)*/ {
        binding.rv.layoutManager = LinearLayoutManager(
            this@CharacterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rv.adapter = characterAdapter
    }

    private fun onClickItem(character: Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CharacterKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }


}
