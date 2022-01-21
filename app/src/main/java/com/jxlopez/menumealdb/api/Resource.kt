package com.jxlopez.menumealdb.api

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message:String?,
    val error: Error?
){
    companion object{

        fun <T> success(data:T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg:String, data:T?, error: Error): Resource<T>{
            return Resource(Status.ERROR, data, msg, error)
        }
    }
}

data class Error(
    val errorCode: Int,
    val errorMessage: String
)