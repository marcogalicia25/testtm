package com.mswim.architecture.mvp;

public interface BaseInteractor<T> {
    void loadDataCallBack(T data);

    void error(int codeError, String error);
}
