package com.project.employee.webservices;

import retrofit2.Retrofit;

public class RestClient {


    public static final String Base_url = "http://dummy.restapiexample.com/api/v1/";
    public static Retrofit client = new Retrofit.Builder().baseUrl(Base_url).build();

}