package com.mswim.architecture.mvp;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.UiThread;


public interface MvpPresenter <V extends MvpView>{

    @UiThread
    void attachView(V view);

    @UiThread
    void detachView();

    void attachLifecycle(Lifecycle lifecycle);

    void detachLifecycle(Lifecycle lifecycle);

}
