package com.hoodlums.linkbucket.data.models


data class LinkPostBody(
    val title: String,
    val link: String,
    val `public`: Boolean,
    val tags: String
)