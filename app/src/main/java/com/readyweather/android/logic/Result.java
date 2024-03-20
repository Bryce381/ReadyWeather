package com.readyweather.android.logic;

public class Result<T> {
    private T value;
    private Exception error;

    private Result(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Exception error) {
        return new Result<>(null, error);
    }

    public T getValue() {
        return value;
    }

    public Exception getError() {
        return error;
    }

    public boolean isSuccess() {
        return value != null && error == null;
    }
    //实现getOrNull和exceptionOrNull


    public T getOrNull() {
        if (isFailure()) {
            return null;
        } else {
            return (T) value; // 假设getRight()方法获取成功时的值
        }
    }

    public Throwable exceptionOrNull() {
        if (value instanceof kotlin.Result.Failure) {
            return ((kotlin.Result.Failure) value).exception;
        } else {
            return null;
        }
    }
    public boolean isFailure() {
        return error != null;
    }
}
