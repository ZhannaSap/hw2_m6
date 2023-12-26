package com.example.hw2_m6.ui.second_activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.hw2_m6.R
import com.example.hw2_m6.databinding.ActivityDetailsBinding
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.ui.utils.CharacterKeys
import com.example.hw2_m6.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }

        }

    }

    private fun setCharacterData(it: Character) = with(binding) {
        Log.e("ololo", "Data is not null")
        tvCharacterName.text = it.name
        tvLocationInfo.text = it.location.toString()
        tvOriginInfo.text = it.origin.toString()
        tvSpecies.text = it.species
        tvStatus.text = it.status
        Glide.with(imageCharacter).load(it.image).into(imageCharacter)

        when (it.status) {
            Status.ALIVE.provider -> circleStatus.setBackgroundResource(R.drawable.circle_green)
            Status.DEAD.provider -> circleStatus.setBackgroundResource(R.drawable.circle_red)
            Status.UNKNOWN.provider -> circleStatus.setBackgroundResource(R.drawable.circle)
        }
    }
}