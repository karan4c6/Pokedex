package com.karansyd4.pokedex.data.model

sealed class Result<out R> {

    companion object {
        // Network Response Codes
        const val NETWORK_OK = 200
        const val NETWORK_ERROR = 500
    }

    /**
     * Default Initial State
     */
    object Loading : Result<Nothing>()

    /**
     * Success response
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Server / Network Error
     */
    data class NetworkError<out T>(val cacheData: T) : Result<T>()

    /**
     * Database Operation Error
     */
    data class DatabaseError(val message: String) : Result<Nothing>()

    /**
     * General Error
     */
    data class Error(val message: String) : Result<Nothing>()

    /**
     * When network returns invalid json data
     */
    data class InvalidResponseError(val exception: Exception) : Result<Nothing>()

}