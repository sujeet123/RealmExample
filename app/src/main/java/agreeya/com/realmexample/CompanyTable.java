package agreeya.com.realmexample;

import io.realm.RealmObject;

/**
 * Created by sujeet.kumar on 21-04-2016.
 */
public class CompanyTable extends RealmObject {

    private int emp_id;
    private  String emp_name;
    private String age;
    private String address;

    public String getEmp_name() {
        return this.emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public int getEmp_id() {
        return this.emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {

        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
