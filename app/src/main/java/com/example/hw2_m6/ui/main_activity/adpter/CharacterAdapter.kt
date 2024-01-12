package com.example.hw2_m6.ui.main_activity.adpter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw2_m6.R
import com.example.hw2_m6.data.model.Character
import com.example.hw2_m6.databinding.ItemCaharacterBinding
import com.example.hw2_m6.ui.utils.Status

class CharacterAdapter(
    private val onClick: (character: Character) -> Unit,
) : ListAdapter<Character, CharacterViewHolder>(
    CharacterDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        ItemCaharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onClick

    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CharacterViewHolder(
    private val binding: ItemCaharacterBinding,
    private val onClick: (character: Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor")
    fun bind(character: Character) {
        binding.run {
            Glide.with(imageCharacter).load(character.image)
                .into(imageCharacter)
            tvCharacterName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = character.species
            tvLocationInfo.text = character.location.name
            itemView.setOnClickListener {
                onClick(character)
            }

            when (character.status) {
                Status.ALIVE.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
                Status.DEAD.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
                Status.UNKNOWN.provider -> imgCircleStatus.setBackgroundResource(R.drawable.circle)
            }
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem

}
