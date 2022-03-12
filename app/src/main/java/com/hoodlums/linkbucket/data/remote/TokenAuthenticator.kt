package com.hoodlums.linkbucket.data.remote

import com.hoodlums.linkbucket.core.constants.BucketConstants.AUTHORIZATION
import com.hoodlums.linkbucket.data.local.SharedPreferenceManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val sharedPref: SharedPreferenceManager) : Authenticator {

    private var token: String? = null

    override fun authenticate(route: Route?, response: Response): Request? {
        token = sharedPref.getUserToken()
        // Add new header to rejected request and retry it
        return token?.let {
            response.request.newBuilder()
                .header(AUTHORIZATION, "Bearer $it")
                .build()
        }

    }

}