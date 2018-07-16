package com.mswim.architecture.mvp;

import android.arch.lifecycle.ViewModel;

public final class BaseViewModel<PRESENTER extends BasePresenter> extends ViewModel {

    private PRESENTER presenter;

    public void setPresenter(PRESENTER presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    public PRESENTER getPresenter() {
        return this.presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
