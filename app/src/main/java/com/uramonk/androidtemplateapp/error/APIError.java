package com.uramonk.androidtemplateapp.error;

/**
 * Created by uramonk on 2016/06/22.
 */
public class ApiError {
    public final int cod;
    public final String message;

    public ApiError(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    public ApiStatus getAPIStatus() {
        return convertAPIStatus(cod);
    }

    private ApiStatus convertAPIStatus(int cod) {
        if (cod == ApiStatus.BAD_RESPONSE.getValue()) {
            return ApiStatus.BAD_RESPONSE;
        } else if (cod == ApiStatus.NETWORK_ERROR.getValue()) {
            return ApiStatus.NETWORK_ERROR;
        } else if (cod == ApiStatus.BAD_REQUEST.getValue()) {
            return ApiStatus.BAD_REQUEST;
        } else if (cod == ApiStatus.UNAUTHORIZED.getValue()) {
            return ApiStatus.UNAUTHORIZED;
        } else if (cod == ApiStatus.PAYMENT_REQUIRED.getValue()) {
            return ApiStatus.PAYMENT_REQUIRED;
        } else if (cod == ApiStatus.FORBIDDEN.getValue()) {
            return ApiStatus.FORBIDDEN;
        } else if (cod == ApiStatus.NOT_FOUND.getValue()) {
            return ApiStatus.NOT_FOUND;
        } else if (cod == ApiStatus.METHOD_NOT_ALLOWED.getValue()) {
            return ApiStatus.METHOD_NOT_ALLOWED;
        } else if (cod == ApiStatus.NOT_ACCEPTABLE.getValue()) {
            return ApiStatus.NOT_ACCEPTABLE;
        } else if (cod == ApiStatus.PROXY_AUTHENTICATION_REQUIRED.getValue()) {
            return ApiStatus.PROXY_AUTHENTICATION_REQUIRED;
        } else if (cod == ApiStatus.REQUEST_TIMEOUT.getValue()) {
            return ApiStatus.REQUEST_TIMEOUT;
        } else if (cod == ApiStatus.CONFLICT.getValue()) {
            return ApiStatus.CONFLICT;
        } else if (cod == ApiStatus.GONE.getValue()) {
            return ApiStatus.GONE;
        } else if (cod == ApiStatus.INTERNAL_SERVER_ERROR.getValue()) {
            return ApiStatus.INTERNAL_SERVER_ERROR;
        } else if (cod == ApiStatus.NOT_IMPLEMENTED.getValue()) {
            return ApiStatus.NOT_IMPLEMENTED;
        } else if (cod == ApiStatus.BAD_GATEWAY.getValue()) {
            return ApiStatus.BAD_GATEWAY;
        } else if (cod == ApiStatus.SERVICE_UNAVAIABLE.getValue()) {
            return ApiStatus.SERVICE_UNAVAIABLE;
        }
        return ApiStatus.UNKNOWN;
    }
}
