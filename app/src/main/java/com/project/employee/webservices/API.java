package com.project.employee.webservices;

import com.project.employee.response.EmployeeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("employees")
    Call<EmployeeResponse> getEmployee ();
}

