package com.mswim.general.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mswim.architecture.BaseActivity;
import com.mswim.general.R;
import com.mswim.general.domain.model.Project;
import com.mswim.general.presentation.presenter.MainPresenter;
import com.mswim.general.presentation.ui.adapter.RecyclerAdapter;
import com.mswim.general.presentation.view.MainView;
import com.mswim.general.utils.ConstantHelper;
import com.mswim.general.utils.GeneralLog;
import com.mswim.general.utils.NetworkHelper;
import com.mswim.general.utils.OnItemClickListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, RadioGroup.OnCheckedChangeListener, OnItemClickListener {

    private final String TAG = "MainActivity";

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;

    @BindView(R.id.radioGroup2)
    RadioGroup radioGroup2;

    @BindView(R.id.linear_layout_error)
    LinearLayout errorLayout;

    @BindView(R.id.text_error_layout)
    TextView errorTextLayout;

    @BindView(R.id.image_error_layout)
    ImageView errorImageLayout;

    @BindView(R.id.total_projects)
    TextView totalProjects;

    private RecyclerAdapter adapter;
    private boolean isChecking = true;

    @StringDef({STATUS.ALL, STATUS.ACTIVE, STATUS.ARCHIVED, STATUS.CURRENT, STATUS.LATE, STATUS.COMPLETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface STATUS {
        String ALL = "all";
        String ACTIVE = "active";
        String ARCHIVED = "archived";
        String CURRENT = "current";
        String LATE = "late";
        String COMPLETED = "completed";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.projects);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getAllProjects();
            }
        });
        if (savedInstanceState == null) {
            radioGroup1.check(R.id.radioButtonAll);
        }
        getPresenter().getAllProjects();
        radioGroup1.setOnCheckedChangeListener(this);
        radioGroup2.setOnCheckedChangeListener(this);
        adapter = new RecyclerAdapter(this, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void showErrorView(boolean showError) {
        if (showError) {
            recyclerView.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading(boolean loading) {
        swipeRefresh.setRefreshing(loading);
    }

    @Override
    public void showError(int errorCode, String error) {
        showErrorView(true);
    }

    @Override
    public void setData(List<Project> data) {
        if (data != null && data.size() > 0) {
            showErrorView(false);
            adapter.setProjects(data);
            adapter.notifyDataSetChanged();
            totalProjects.setText(String.format(Locale.getDefault(), "%d", data.size()));
        } else {
            setNoneProjectsLayout();
            totalProjects.setText("0");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId != -1 && isChecking) {
            isChecking = false;
            switch (group.getId()) {
                case R.id.radioGroup1:
                    radioGroup2.clearCheck();
                    break;
                case R.id.radioGroup2:
                    radioGroup1.clearCheck();
                    break;
            }

            switch (checkedId) {
                case R.id.radioButtonAll:
                    getPresenter().setFilteredProjects(STATUS.ALL);
                    break;
                case R.id.radioButtonCompleted:
                    getPresenter().setFilteredProjects(STATUS.COMPLETED);
                    break;
                case R.id.radioButtonArchived:
                    getPresenter().setFilteredProjects(STATUS.ARCHIVED);
                    break;
                case R.id.radioButtonLate:
                    getPresenter().setFilteredProjects(STATUS.LATE);
                    break;
                case R.id.radioButtonActive:
                    getPresenter().setFilteredProjects(STATUS.ACTIVE);
                    break;
                case R.id.radioButtonCurrent:
                    getPresenter().setFilteredProjects(STATUS.CURRENT);
                    break;
            }
        }
        isChecking = true;
    }

    private void setNoneProjectsLayout() {
        recyclerView.setVisibility(View.GONE);
        errorLayout.setVisibility(View.VISIBLE);
        errorTextLayout.setText(R.string.project_none);
        errorImageLayout.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.none_projects));
    }

    @Override
    public void finish() {
        getPresenter().clearFilteredProjectsArray();
        super.finish();
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
    }

    @Override
    public void onItemClick(Project project) {
        if (project != null) {
            Intent intent = new Intent(this, ProjectActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantHelper.PROJECT, project);
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in_slide_up, R.anim.no_anim);
        }
    }
}
