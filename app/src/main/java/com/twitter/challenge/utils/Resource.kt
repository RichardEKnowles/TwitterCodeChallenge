package com.twitter.challenge.utils

data class Resource<out ResultType>(val status: Status, val data: ResultType?, val message: String?) {
    companion object {

        fun <ResultType> success(data: ResultType?): Resource<ResultType> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <ResultType> error(msg: String, data: ResultType?): Resource<ResultType> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <ResultType> loading(data: ResultType?): Resource<ResultType> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}