package com.bestapplication.tikiandroidtest

data class Response<T>(
        val status: Status,
        val data: T?,
        val error: Throwable?
) {

    enum class Status {
        LOADING,
        SUCCEED,
        FAILED
    }

    companion object {
        fun <T> succeed(data: T): Response<T> {
            return Response(Status.SUCCEED, data, null)
        }

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, null)
        }

        fun <T> error(t: Throwable): Response<T> {
            return Response(Status.FAILED, null, t)
        }
    }
}