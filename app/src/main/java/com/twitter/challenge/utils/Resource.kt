package com.twitter.challenge.utils

import androidx.annotation.StringRes
import com.twitter.challenge.R

data class Resource<out ResultType>(val status: Status, val data: ResultType?, @StringRes val messageRes: Int?) {
    companion object {

        fun <ResultType> success(data: ResultType?): Resource<ResultType> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <ResultType> error(errorType: ErrorType, data: ResultType?): Resource<ResultType> {
            return Resource(Status.ERROR, data, errorType.msgStringRes)
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

enum class ErrorType(@StringRes val msgStringRes: Int) {
    GENERIC(R.string.error_msg_generic),
    NETWORK_ERROR(R.string.error_msg_network),
    NO_INTERNET_DETECTED(R.string.error_msg_no_internet_detected)
}