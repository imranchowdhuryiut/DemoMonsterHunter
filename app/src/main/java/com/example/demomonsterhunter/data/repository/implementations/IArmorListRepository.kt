package com.example.demomonsterhunter.data.repository.implementations

import com.example.demomonsterhunter.data.model.Armor
import com.example.demomonsterhunter.data.network.ApiClient.createApiService
import com.example.demomonsterhunter.data.network.ApiResponse
import com.example.demomonsterhunter.data.network.service.ArmorService
import com.example.demomonsterhunter.data.repository.interfaces.ArmorListRepository

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

class IArmorListRepository : ArmorListRepository {

    private val mWebService = createApiService<ArmorService>()

    override suspend fun getArmorList(): ApiResponse<List<Armor>> {
        val response =  mWebService.getArmorList()
        return if (response.isSuccessful) {
            ApiResponse(true, "", response.body())
        } else {
            ApiResponse(false, "Something went wrong.", null)
        }
    }

}