package com.mswim.general.presentation.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mswim.general.R;
import com.mswim.general.domain.model.Project;
import com.mswim.general.domain.model.ProjectResponse;
import com.mswim.general.utils.DateHelper;
import com.mswim.general.utils.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.ACTIVE;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.ARCHIVED;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.COMPLETED;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.CURRENT;
import static com.mswim.general.presentation.ui.activity.MainActivity.STATUS.LATE;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private final Resources resource;
    private List<Project> projects;
    private OnItemClickListener listener;
    private Context context;

    public RecyclerAdapter(Context context, List<Project> cities) {
        this.projects = cities;
        this.context = context;
        resource = context.getResources();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_project_name)
        TextView projectName;

        @BindView(R.id.text_company_name)
        TextView companyName;


        @BindView(R.id.image_project)
        ImageView imageProject;

        @BindView(R.id.starred_view)
        ImageView starredView;

        @BindView(R.id.status_view)
        LinearLayout statusView;

        @BindView(R.id.text_project_createdon)
        TextView createdOn;

        @BindView(R.id.text_project_enddate)
        TextView endAt;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(projects.get(getLayoutPosition()));
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ItemViewHolder holder, int position) {
        Project project = projects.get(position);

        switch (project.getStatus()) {
            case ACTIVE:
                holder.statusView.setBackgroundColor(ContextCompat.getColor(context, R.color.active));
                break;
            case COMPLETED:
                holder.statusView.setBackgroundColor(ContextCompat.getColor(context, R.color.completed));
                break;
            case LATE:
                holder.statusView.setBackgroundColor(ContextCompat.getColor(context, R.color.late));
                break;
            case CURRENT:
                holder.statusView.setBackgroundColor(ContextCompat.getColor(context, R.color.current));
                break;
            case ARCHIVED:
                holder.statusView.setBackgroundColor(ContextCompat.getColor(context, R.color.archived));
                break;
        }
        holder.projectName.setText(project.getName());
        if (project.getCompany() != null) {
            holder.companyName.setText(project.getCompany().getName());
        }
        if (project.getStarred()) {
            holder.starredView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checked));
        } else {
            holder.starredView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.uncheked));
        }
        holder.createdOn.setText(resource.getString(R.string.project_createdon, DateHelper.parseDateTo(project.getCreatedOn())));
        holder.endAt.setText(resource.getString(R.string.project_endDate, project.getEndDate()));
        Glide.with(context).load(project.getLogo()).apply(RequestOptions.circleCropTransform()).into(holder.imageProject);

    }

    @Override
    public int getItemCount() {
        return projects != null ? projects.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void clearDatas() {
        if (projects != null && projects.size() > 0) {
            projects.clear();
        }
    }
}


