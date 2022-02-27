package com.example.demomonsterhunter.data.network

/**
 * Created by Imran Chowdhury on 2/26/2022.
 */

class Resource<T> private constructor(val status: Status, val data: T?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(errorData: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                errorData
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data
            )
        }
    }
}