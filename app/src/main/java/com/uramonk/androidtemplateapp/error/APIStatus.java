package com.uramonk.androidtemplateapp.error;

/**
 * Created by uramonk on 2016/06/22.
 */
public enum ApiStatus {
    BAD_RESPONSE(1), NETWORK_ERROR(2),
    BAD_REQUEST(400), UNAUTHORIZED(401), PAYMENT_REQUIRED(402), FORBIDDEN(403), NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405), NOT_ACCEPTABLE(406), PROXY_AUTHENTICATION_REQUIRED(407), REQUEST_TIMEOUT(408),
    CONFLICT(409), GONE(410),
    INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(501), BAD_GATEWAY(502), SERVICE_UNAVAIABLE(503),
    UNKNOWN(-1);

    private final int value;

    ApiStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
