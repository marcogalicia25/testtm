package com.mswim.architecture.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

public class BasePresenter<VIEW extends MvpView> implements MvpPresenter<VIEW>, LifecycleObserver {

    private WeakReference<VIEW> viewRef;

    @UiThread
    @Override
    public void attachView(VIEW view) {
        viewRef = new WeakReference<>(view);
    }

    @UiThread
    @Nullable
    public VIEW getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @UiThread
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @UiThread
    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }
}
