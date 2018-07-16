package com.mswim.architecture;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mswim.architecture.mvp.BasePresenter;
import com.mswim.architecture.mvp.BaseViewModel;
import com.mswim.architecture.mvp.MvpDelegateCallback;
import com.mswim.architecture.mvp.MvpView;


public abstract class BaseFragment<VIEW extends MvpView, PRESENTER extends BasePresenter<VIEW>> extends Fragment implements MvpDelegateCallback<VIEW, PRESENTER>, MvpView {

    protected PRESENTER presenter;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseViewModel<PRESENTER> viewModel = ViewModelProviders.of(this).get(BaseViewModel.class);
        if (viewModel.getPresenter() == null) {
            viewModel.setPresenter(createPresenter());
        }
        presenter = viewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((VIEW) this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachLifecycle(getLifecycle());
            presenter.detachView();
        }
    }
}
