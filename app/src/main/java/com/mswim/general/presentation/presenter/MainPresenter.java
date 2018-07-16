package com.mswim.general.presentation.presenter;

import com.mswim.architecture.mvp.BasePresenter;
import com.mswim.general.domain.interactor.MainInteractor;
import com.mswim.general.domain.interactor.MainInteractorImp;
import com.mswim.general.domain.model.Project;
import com.mswim.general.presentation.ui.activity.MainActivity;
import com.mswim.general.presentation.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainView> implements MainInteractor {
    private MainInteractorImp interactor;
    private List<Project> projects;
    private List<Project> filteredProjects = new ArrayList<>();

    public MainPresenter() {
        interactor = new MainInteractorImp(this);
    }

    public void getAllProjects() {
        if (projects == null || projects.size() == 0) {
            if (isViewAttached()) {
                getView().showLoading(true);
                interactor.getAllProyects();
            }
        }
    }

    @Override
    public void loadDataCallBack(List<Project> data) {
        projects = new ArrayList<>(data);
        if (isViewAttached()) {
            getView().showLoading(false);
            getView().setData(projects);
        }
    }

    @Override
    public void error(int codeError, String error) {
        if (isViewAttached()) {
            getView().showLoading(false);
            getView().showError(codeError, error);
        }
    }

    public void setFilteredProjects(String status) {
        if (projects == null || projects.size() == 0) {
            return;
        }
        clearFilteredProjectsArray();
        if (status.equals(MainActivity.STATUS.ALL)) {
            if (isViewAttached()) {
                getView().setData(projects);
            }
            return;
        }
        if (projects != null && projects.size() > 0) {
            for (Project project : projects) {
                if (project.getStatus().equals(status)) {
                    filteredProjects.add(project);
                }
            }
        }

        if (isViewAttached()) {
            getView().setData(filteredProjects);
        }
    }

    public void clearFilteredProjectsArray() {
        if (filteredProjects != null) {
            filteredProjects.clear();
        }
    }
}
