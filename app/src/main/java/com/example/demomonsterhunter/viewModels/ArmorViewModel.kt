package com.example.demomonsterhunter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demomonsterhunter.data.model.Armor
import com.example.demomonsterhunter.data.network.ApiResponse
import com.example.demomonsterhunter.data.network.Resource
import com.example.demomonsterhunter.data.repository.implementations.IArmorListRepository
import com.example.demomonsterhunter.data.repository.interfaces.ArmorListRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

class ArmorViewModel : ViewModel() {

    private val mRepo: ArmorListRepository by lazy { IArmorListRepository() }

    var armorLists: Sequence<Armor> = sequenceOf()

    fun getArmorList(): LiveData<Resource<ApiResponse<List<Armor>>>> {
        return requestApiData {
            mRepo.getArmorList()
        }
    }

    private fun <T> requestApiData(
        processData: ((T?) -> Unit)? = null,
        requestApiResponse: suspend () -> ApiResponse<T>?
    ): LiveData<Resource<ApiResponse<T>>> {
        return liveData(Dispatchers.Default) {
            emit(Resource.loading())
            val data = requestApiResponse()
            if (data?.status == true) {
                processData?.invoke(data.data)
                emit(Resource.success(data))
            } else emit(Resource.error(data))
        }
    }

}