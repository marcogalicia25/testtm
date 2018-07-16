package com.mswim.architecture.mvp;

import android.support.annotation.NonNull;

public interface MvpDelegateCallback <V extends MvpView, P extends MvpPresenter<V>> {
    @NonNull
    public P createPresenter();
    public P getPresenter();
    public V getMvpView();
}
