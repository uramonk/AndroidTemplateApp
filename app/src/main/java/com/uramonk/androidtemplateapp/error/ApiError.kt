package com.uramonk.androidtemplateapp.error

/**
 * Created by uramonk on 2016/06/22.
 */
class ApiError(val cod: Int, val message: String?) {

    val apiStatus: ApiStatus
        get() = convertAPIStatus(cod)

    private fun convertAPIStatus(cod: Int): ApiStatus {
        if (cod == ApiStatus.BAD_RESPONSE.value) {
            return ApiStatus.BAD_RESPONSE
        } else if (cod == ApiStatus.NETWORK_ERROR.value) {
            return ApiStatus.NETWORK_ERROR
        } else if (cod == ApiStatus.BAD_REQUEST.value) {
            return ApiStatus.BAD_REQUEST
        } else if (cod == ApiStatus.UNAUTHORIZED.value) {
            return ApiStatus.UNAUTHORIZED
        } else if (cod == ApiStatus.PAYMENT_REQUIRED.value) {
            return ApiStatus.PAYMENT_REQUIRED
        } else if (cod == ApiStatus.FORBIDDEN.value) {
            return ApiStatus.FORBIDDEN
        } else if (cod == ApiStatus.NOT_FOUND.value) {
            return ApiStatus.NOT_FOUND
        } else if (cod == ApiStatus.METHOD_NOT_ALLOWED.value) {
            return ApiStatus.METHOD_NOT_ALLOWED
        } else if (cod == ApiStatus.NOT_ACCEPTABLE.value) {
            return ApiStatus.NOT_ACCEPTABLE
        } else if (cod == ApiStatus.PROXY_AUTHENTICATION_REQUIRED.value) {
            return ApiStatus.PROXY_AUTHENTICATION_REQUIRED
        } else if (cod == ApiStatus.REQUEST_TIMEOUT.value) {
            return ApiStatus.REQUEST_TIMEOUT
        } else if (cod == ApiStatus.CONFLICT.value) {
            return ApiStatus.CONFLICT
        } else if (cod == ApiStatus.GONE.value) {
            return ApiStatus.GONE
        } else if (cod == ApiStatus.INTERNAL_SERVER_ERROR.value) {
            return ApiStatus.INTERNAL_SERVER_ERROR
        } else if (cod == ApiStatus.NOT_IMPLEMENTED.value) {
            return ApiStatus.NOT_IMPLEMENTED
        } else if (cod == ApiStatus.BAD_GATEWAY.value) {
            return ApiStatus.BAD_GATEWAY
        } else if (cod == ApiStatus.SERVICE_UNAVAIABLE.value) {
            return ApiStatus.SERVICE_UNAVAIABLE
        }
        return ApiStatus.UNKNOWN
    }
}
