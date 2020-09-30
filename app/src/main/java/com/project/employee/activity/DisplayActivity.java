package com.project.employee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.project.employee.adapter.EmployeeAdapter;
import com.project.employee.R;
import com.project.employee.helper.Common;
import com.project.employee.helper.ConnectionDetector;
import com.project.employee.helper.SharedPreferenceManager;
import com.project.employee.response.EmployeeResponse;
import com.project.employee.sqlmanager.SQLiteManager;
import com.project.employee.webservices.API;
import com.project.employee.webservices.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView rvEmployee;
    ConnectionDetector cd;
    Common common;
    EmployeeResponse employeeResponse;
    ArrayList<EmployeeResponse.data> responseArrayList;
    EmployeeAdapter employeeAdapter;
    SQLiteManager sqLiteManager;
    ArrayList<EmployeeResponse.data> cache = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        rvEmployee = findViewById(R.id.rv_employee);

        cd = new ConnectionDetector(this);
        common = new Common();
        sqLiteManager = new SQLiteManager(this);

        checkDataExits();

    }

    private void callEmployeeApi() {

        if (cd.isConnectingToInternet()) {
            final ProgressDialog dialog = ProgressDialog.show(DisplayActivity.this, "", "Please Wait...");
            dialog.show();
            //     OkHttpClient okClient = new OkHttpClient();
            RestClient.client = new Retrofit.Builder().baseUrl(RestClient.Base_url).client(common.getUnsafeOkHttpClient().build()).addConverterFactory(GsonConverterFactory.create()).build();
            API api = RestClient.client.create(API.class);

            Call<EmployeeResponse> call = api.getEmployee();

            call.enqueue(new Callback<EmployeeResponse>() {

                @Override
                public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                    dialog.dismiss();
                    System.out.println("EmployeeResponse--" + response.body());
                    employeeResponse = response.body();

                    if (employeeResponse != null && employeeResponse.getStatus() != null && employeeResponse.getStatus().equalsIgnoreCase("success")) {

                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();

                        if (SharedPreferenceManager.getTimestamp(DisplayActivity.this).equalsIgnoreCase("")) {
                            SharedPreferenceManager.setTimestamp(DisplayActivity.this, ts);
                        }
                        responseArrayList = new ArrayList<>();

                        for(int i=0;i<employeeResponse.getData().length;i++){
                            System.out.println(employeeResponse.getData()[i]);
                            responseArrayList.add(employeeResponse.getData()[i]);
                            sqLiteManager.addEmployeeData(employeeResponse.getData()[i]);
                        }


                        employeeAdapter = new EmployeeAdapter(responseArrayList,DisplayActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DisplayActivity.this);
                        rvEmployee.setLayoutManager(layoutManager);
                        rvEmployee.setItemAnimator(new DefaultItemAnimator());
                        rvEmployee.setAdapter(employeeAdapter);



                    } else {

                        common.showMessage(DisplayActivity.this,"DATA NOT AVAILABLE");

                    }

                }


                @Override
                public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                    dialog.dismiss();
                    System.out.println("onFailure--" + t.getMessage());
                    common.showMessage(DisplayActivity.this, "Failed....");

                }

            });

        } else {

            common.showMessage(DisplayActivity.this, "Please Check Your Network Connection..");

        }

    }

    private void checkDataExits() {

        if (SharedPreferenceManager.getTimestamp(this).equalsIgnoreCase("")) {

            callEmployeeApi();

        } else {

            Long currentTimeStamp = System.currentTimeMillis() / 1000;

            String timeStamp = SharedPreferenceManager.getTimestamp(this);

            long insertedTimeStamp = Long.parseLong(timeStamp);
            System.out.print(insertedTimeStamp);

            if (Math.abs(insertedTimeStamp - currentTimeStamp) > 10800) {

                System.out.println("\n Timestamp difference : " + (insertedTimeStamp - currentTimeStamp));

                sqLiteManager.deleteAllEmployee();

            } else {

                display_from_DB();

            }

        }

    }


    public void display_from_DB(){
        cache = sqLiteManager.getSavedEmployee();

        employeeAdapter = new EmployeeAdapter(cache,DisplayActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DisplayActivity.this);
        rvEmployee.setLayoutManager(layoutManager);
        rvEmployee.setItemAnimator(new DefaultItemAnimator());
        rvEmployee.setAdapter(employeeAdapter);


    }

}
