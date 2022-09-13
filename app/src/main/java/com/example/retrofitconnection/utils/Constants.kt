package com.example.retrofitconnection.utils

object Constants {
    const val TAG : String = "test-jennet"
    val TABS = arrayOf("MOSHI", "GSON")
}

enum class SEARCH_TYPE {
    PHOTO,
    USER
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API {
    const val BASE_URL ="https://api.unsplash.com/"
    const val CLIENT_ID  = "2kPRCHud1L3_YS_-66YWVLg-Q_uBeHhx7bnThxfEohU"
    const val SEARCH_PHOTOS = "search/photos"
    const val SEARCH_USERS = "search/users"
}