package com.example.demomonsterhunter.utills

import androidx.recyclerview.widget.DiffUtil
import com.example.demomonsterhunter.data.model.Armor

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */
object ArmorItemDiff: DiffUtil.ItemCallback<Armor>() {
    override fun areItemsTheSame(oldItem: Armor, newItem: Armor): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Armor, newItem: Armor): Boolean {
        return oldItem.id == newItem.id
    }
}