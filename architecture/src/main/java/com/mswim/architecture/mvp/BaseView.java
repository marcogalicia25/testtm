package com.mswim.architecture.mvp;

import android.support.annotation.UiThread;

public interface BaseView<M> extends MvpView {

    @UiThread
    public void showLoading(boolean loading);

    @UiThread
    public void showError(int errorCode, String error);

    @UiThread
    public void setData(M data);

}
