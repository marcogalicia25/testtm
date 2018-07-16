package com.mswim.general.domain.interactor;

import android.support.annotation.NonNull;

import com.mswim.general.domain.model.ProjectResponse;
import com.mswim.general.repository.network.EndPoints;
import com.mswim.general.utils.APIRetrofitClient;
import com.mswim.general.utils.ConstantHelper;

import java.lang.ref.WeakReference;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractorImp {

    private WeakReference<MainInteractor> weakInterf;
    private boolean isWorking;

    public MainInteractorImp(MainInteractor interf) {
        weakInterf = new WeakReference<>(interf);
    }

    public void getAllProyects() {
        if (isWorking) {
            return;
        }
        isWorking = true;
        EndPoints apiService = APIRetrofitClient.getClient().create(EndPoints.class);
        Call<ProjectResponse> call = apiService.getAllProjects();
        call.enqueue(new Callback<ProjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProjectResponse> call, @NonNull Response<ProjectResponse> response) {
                isWorking = false;
                if (response.isSuccessful()) {
                    ProjectResponse bodyResponse = response.body();
                    if (bodyResponse != null && bodyResponse.getSTATUS().equals(ConstantHelper.OK)) {
                        if (weakInterf.get() != null) {
                            weakInterf.get().loadDataCallBack(bodyResponse.getProjects());
                        }
                        return;
                    }
                }
                if (weakInterf.get() != null) {
                    weakInterf.get().error(0, "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProjectResponse> call, @NonNull Throwable t) {
                isWorking = false;
                if (weakInterf.get() != null) {
                    weakInterf.get().error(0, "");
                }
            }
        });
    }
}
