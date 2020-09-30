package com.project.employee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.employee.R;
import com.project.employee.activity.DisplayActivity;
import com.project.employee.response.EmployeeResponse;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    ArrayList<EmployeeResponse.data> arrayList;
    DisplayActivity context;

    public EmployeeAdapter(ArrayList<EmployeeResponse.data> arrayList,DisplayActivity context){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvId.setText("ID : "+arrayList.get(position).getId().toString().trim());
        holder.tvName.setText("Name : "+arrayList.get(position).getEmployee_name().toString().trim());
        holder.tvSalary.setText("Salary : "+arrayList.get(position).getEmployee_salary().toString().trim());
        holder.tvAge.setText("Age : "+arrayList.get(position).getEmployee_age().toString().trim());

        String image = arrayList.get(position).getProfile_image().toString().trim();

        if(!image.equalsIgnoreCase("")){

            Picasso.get().load(image).fit().centerCrop().into(holder.ivProfile);
        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvAge,tvSalary,tvId;
        ImageView ivProfile;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvSalary = itemView.findViewById(R.id.tv_salary);
            tvId = itemView.findViewById(R.id.tv_id);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }
    }
}
