package com.example.demomonsterhunter.data.repository.interfaces

import com.example.demomonsterhunter.data.model.Armor
import com.example.demomonsterhunter.data.network.ApiResponse

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

interface ArmorListRepository {
    suspend fun getArmorList():ApiResponse<List<Armor>>
}