package com.sinelnikov.bsc.model

class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> init(data: T?): Resource<T> {
            return Resource(Status.INIT, data, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}