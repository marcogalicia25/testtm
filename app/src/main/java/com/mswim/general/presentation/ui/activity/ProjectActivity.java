package com.mswim.general.presentation.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mswim.architecture.BaseActivity;
import com.mswim.architecture.mvp.BaseView;
import com.mswim.general.R;
import com.mswim.general.domain.model.Project;
import com.mswim.general.presentation.presenter.ProjectDetailPresenter;
import com.mswim.general.utils.ConstantHelper;
import com.mswim.general.utils.DateHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.ACTIVE;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.ARCHIVED;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.COMPLETED;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.CURRENT;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.LATE;

public class ProjectActivity extends BaseActivity<BaseView, ProjectDetailPresenter> implements BaseView {

    private final String TAG = "MainActivity";

    @BindView(R.id.layout_main_header)
    LinearLayout headerLayout;

    @BindView(R.id.image_logo_project)
    ImageView imageLogoProject;

    @BindView(R.id.image_button_back)
    ImageView buttonBack;

    @BindView(R.id.text_project_createdon_detail)
    TextView createdTextView;

    @BindView(R.id.text_project_enddate_detail)
    TextView endDateTextView;

    @BindView(R.id.text_project_changeddate_detail)
    TextView changedDateTextView;

    @BindView(R.id.company_name_detail)
    TextView companyTextView;

    @BindView(R.id.text_description)
    TextView descriptionTextView;

    @BindView(R.id.name_project_detail)
    TextView nameProjectTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Project project;
        if (savedInstanceState == null) {
            if (bundle != null) {
                project = (Project) bundle.getSerializable(ConstantHelper.PROJECT);
                getPresenter().setProject(project);
            } else {
                finish();
                return;
            }
        } else {
            project = getPresenter().getProject();
        }
        Glide.with(this).load(project.getLogo()).apply(RequestOptions.circleCropTransform()).into(imageLogoProject);
        switch (project.getStatus()) {
            case ACTIVE:
                headerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.active));
                setStatusBarColor(R.color.active);
                break;
            case COMPLETED:
                headerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.completed));
                setStatusBarColor(R.color.completed);
                break;
            case LATE:
                headerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.late));
                setStatusBarColor(R.color.late);
                break;
            case CURRENT:
                headerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.current));
                setStatusBarColor(R.color.current);
                break;
            case ARCHIVED:
                headerLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.archived));
                setStatusBarColor(R.color.archived);
                break;
        }
        nameProjectTextView.setText(project.getName());
        createdTextView.setText(DateHelper.parseDateTo(project.getCreatedOn()));
        endDateTextView.setText(project.getEndDate());
        changedDateTextView.setText(DateHelper.parseDateTo(project.getLastChangedOn()));
        descriptionTextView.setText(project.getDescription());

        if (project.getCompany() != null) {
            companyTextView.setText(project.getCompany().getName());
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @NonNull
    @Override
    public ProjectDetailPresenter createPresenter() {
        return new ProjectDetailPresenter();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_slide_down);
    }

    public void setStatusBarColor(int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, color));
    }

    @Override
    public void showLoading(boolean loading) {

    }

    @Override
    public void showError(int errorCode, String error) {

    }

    @Override
    public void setData(Object data) {

    }
}
