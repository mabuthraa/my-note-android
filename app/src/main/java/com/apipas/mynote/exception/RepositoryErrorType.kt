package com.apipas.mynote.exception

enum class RepositoryErrorType {
    TYPE_NETWORK, // no-internet connection
    TYPE_HTTP, // server error
    TYPE_API, // BE error
    TYPE_API_NO_CONTENT, // No content : can be as alternative error for un-managed #204 res
    UNAUTHOROZIED, // unknown
    TYPE_UNEXPECTED, // unknown
    TYPE_MAPPING // mapping
}