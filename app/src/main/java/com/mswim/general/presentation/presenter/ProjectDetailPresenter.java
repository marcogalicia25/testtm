package com.mswim.general.presentation.presenter;

import com.mswim.architecture.mvp.BasePresenter;
import com.mswim.architecture.mvp.BaseView;
import com.mswim.general.domain.model.Project;

public class ProjectDetailPresenter extends BasePresenter<BaseView> {

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


}
