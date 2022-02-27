package com.example.demomonsterhunter.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demomonsterhunter.R
import com.example.demomonsterhunter.data.model.Armor
import com.example.demomonsterhunter.databinding.ItemArmorBinding
import com.example.demomonsterhunter.utills.ArmorItemDiff

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

class ArmorListAdapter: ListAdapter<Armor, ArmorViewHolder>(ArmorItemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorViewHolder {
        return ArmorViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ArmorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ArmorViewHolder(
    private val binding: ItemArmorBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Armor?) {
        Glide.with(itemView.context)
            .load(item?.assets?.imageMale)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imgArmor)

        binding.tvArmorName.text = item?.name
        binding.tvRank.text = item?.rank
        binding.tvBaseDefence.text = "${item?.defense?.base}+"
    }


    companion object {
        fun createViewHolder(
            parent: ViewGroup
        ): ArmorViewHolder {
            val view = ItemArmorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ArmorViewHolder(view)
        }

    }

}