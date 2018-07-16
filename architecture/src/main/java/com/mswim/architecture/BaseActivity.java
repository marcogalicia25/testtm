package com.mswim.architecture;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.mswim.architecture.mvp.BasePresenter;
import com.mswim.architecture.mvp.BaseViewModel;
import com.mswim.architecture.mvp.MvpDelegateCallback;
import com.mswim.architecture.mvp.MvpView;

public abstract class BaseActivity<VIEW extends MvpView, PRESENTER extends BasePresenter<VIEW>>
        extends AppCompatActivity implements MvpDelegateCallback<VIEW, PRESENTER>, MvpView {

    protected PRESENTER presenter;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel<PRESENTER> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(createPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((VIEW) this);
    }

    @NonNull
    @Override
    public abstract PRESENTER createPresenter();

    @Override
    public PRESENTER getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    public VIEW getMvpView() {
        return (VIEW) this;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }

}
