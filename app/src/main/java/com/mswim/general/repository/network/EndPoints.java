package com.mswim.general.repository.network;

import com.mswim.general.domain.model.ProjectResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoints {
    @GET("/projects.json")
    Call<ProjectResponse> getAllProjects();
}
