package com.hoodlums.linkbucket.core


sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(
        val errorString: String = "",
    ) : Result<Nothing>()
}
