package com.uramonk.androidtemplateapp.error;

/**
 * Created by uramonk on 2016/06/22.
 */
public class APIError {
    public final int cod;
    public final String message;
    public final APIStatus apiStatus;

    public APIError(int cod, String message) {
        this.cod = cod;
        this.message = message;
        this.apiStatus = convertAPIStatus(cod);
    }

    private APIStatus convertAPIStatus(int cod) {
        if (cod == APIStatus.BAD_RESPONSE.getValue()) {
            return APIStatus.BAD_RESPONSE;
        } else if (cod == APIStatus.NETWORK_ERROR.getValue()) {
            return APIStatus.NETWORK_ERROR;
        } else if (cod == APIStatus.BAD_REQUEST.getValue()) {
            return APIStatus.BAD_REQUEST;
        } else if (cod == APIStatus.UNAUTHORIZED.getValue()) {
            return APIStatus.UNAUTHORIZED;
        } else if (cod == APIStatus.PAYMENT_REQUIRED.getValue()) {
            return APIStatus.PAYMENT_REQUIRED;
        } else if (cod == APIStatus.FORBIDDEN.getValue()) {
            return APIStatus.FORBIDDEN;
        } else if (cod == APIStatus.NOT_FOUND.getValue()) {
            return APIStatus.NOT_FOUND;
        } else if (cod == APIStatus.METHOD_NOT_ALLOWED.getValue()) {
            return APIStatus.METHOD_NOT_ALLOWED;
        } else if (cod == APIStatus.NOT_ACCEPTABLE.getValue()) {
            return APIStatus.NOT_ACCEPTABLE;
        } else if (cod == APIStatus.PROXY_AUTHENTICATION_REQUIRED.getValue()) {
            return APIStatus.PROXY_AUTHENTICATION_REQUIRED;
        } else if (cod == APIStatus.REQUEST_TIMEOUT.getValue()) {
            return APIStatus.REQUEST_TIMEOUT;
        } else if (cod == APIStatus.CONFLICT.getValue()) {
            return APIStatus.CONFLICT;
        } else if (cod == APIStatus.GONE.getValue()) {
            return APIStatus.GONE;
        } else if (cod == APIStatus.INTERNAL_SERVER_ERROR.getValue()) {
            return APIStatus.INTERNAL_SERVER_ERROR;
        } else if (cod == APIStatus.NOT_IMPLEMENTED.getValue()) {
            return APIStatus.NOT_IMPLEMENTED;
        } else if (cod == APIStatus.BAD_GATEWAY.getValue()) {
            return APIStatus.BAD_GATEWAY;
        } else if (cod == APIStatus.SERVICE_UNAVAIABLE.getValue()) {
            return APIStatus.SERVICE_UNAVAIABLE;
        }
        return APIStatus.UNKNOWN;
    }
}
