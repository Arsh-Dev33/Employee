package com.project.employee.response;

public class EmployeeResponse {


   String status;
   data data[];


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeResponse.data[] getData() {
        return data;
    }

    public void setData(EmployeeResponse.data[] data) {
        this.data = data;
    }

    //    public void getData() {
//        return void;
//    }
//
//    public void setData(data[] data) {
//        this.data = data;
//    }

    public static class data{

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getEmployee_salary() {
            return employee_salary;
        }

        public void setEmployee_salary(String employee_salary) {
            this.employee_salary = employee_salary;
        }

        public String getEmployee_age() {
            return employee_age;
        }

        public void setEmployee_age(String employee_age) {
            this.employee_age = employee_age;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        String id,employee_name,employee_salary,employee_age,profile_image;

    }
}
