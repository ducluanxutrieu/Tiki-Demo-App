package com.ducluanxutrieu.tikiapp.utiu;

import androidx.annotation.Nullable;

public class ResultError extends Throwable{
    final String message;
    final Throwable cause;

    public ResultError(@Nullable String message, @Nullable Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }
}