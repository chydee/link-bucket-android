package com.jamit.music.core

import com.google.gson.annotations.SerializedName

/**
 * Incase we dont have any specific class for our response
 * we can use this to just organize the response body
 * It can work for both a successful response and a failed one
 */
data class Body(val timestamp: String? = null, val status: Int? = null, val error: String? = null, val message: String? = null, val data: Data? = null)

data class Data(
    val email: String? = null,
    val token: String? = null,
    @SerializedName("code_sent") val code: String? = null
)
