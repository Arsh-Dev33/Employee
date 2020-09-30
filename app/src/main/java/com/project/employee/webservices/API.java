package com.project.employee.webservices;

import com.project.employee.response.EmployeeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("employees")
    Call<EmployeeResponse> getEmployee ();

//    @GET("everything/{source_name}.json")
//    Call<TrendingResponse>  getSource(@Path("source_name") String source_name);
//
//    @GET("top-headlines?")
//    Call<TrendingResponse> getTrends(@Query("country") String country, @Query("category") String category, @Query("apiKey") String key);
}

