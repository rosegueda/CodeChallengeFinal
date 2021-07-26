package POJOS;

import java.util.List;

public class PojoList {

    public class Datum{
        public int id;
        public String employee_name;
        public int employee_salary;
        public int employee_age;
        public String profile_image;
    }

    public class Root{
        public String status;
        public List<Datum> data;
        public String message;
    }

}
