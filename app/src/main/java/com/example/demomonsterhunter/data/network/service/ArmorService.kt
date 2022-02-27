package com.example.demomonsterhunter.data.network.service

import com.example.demomonsterhunter.data.model.Armor
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */
interface ArmorService {

    @GET("armor")
    suspend fun getArmorList(): Response<List<Armor>>

}