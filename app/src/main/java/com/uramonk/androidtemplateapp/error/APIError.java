package com.uramonk.androidtemplateapp.error;

/**
 * Created by uramonk on 2016/06/22.
 */
public class APIError {
    public final int cod;
    public final String message;

    public APIError(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }
}
