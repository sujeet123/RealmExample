package agreeya.com.realmexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.internal.Context;

public class ActivityMainActivity extends Activity {

    @Bind(R.id.textview)
    TextView textview;
    Context context;
    RealmConfiguration realmConfiguration;
    private Realm realm;
    @Bind(R.id.emp_name_et)
    EditText emp_name_et;
    @Bind(R.id.address_et)
    EditText address_et;
    @Bind(R.id.age_et)
    EditText age_et;
    @Bind(R.id.delete_ET)
    EditText delete_ET;
    @Bind(R.id.search_ET)
    EditText search_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realmConfiguration = new RealmConfiguration.Builder(ActivityMainActivity.this).build();

        // Open the Realm for the UI thread.
        realm = Realm.getInstance(realmConfiguration);

    }

    @OnClick(R.id.insert_bt)
    void onInsertBtClick() {
        if (emp_name_et.getText().toString().isEmpty() || address_et.getText().toString().isEmpty() || age_et.getText().toString().isEmpty()) {
            Toast.makeText(ActivityMainActivity.this, "All field are manadotory", Toast.LENGTH_SHORT).show();
            return;
        }
        realm.beginTransaction();
        CompanyTable companyTable = realm.createObject(CompanyTable.class);
        RealmResults<CompanyTable> realmResults = realm.allObjects(CompanyTable.class);
        int size = realmResults.size();
        if (size == 0)
            size = 1;
        companyTable.setEmp_id(++size);
        companyTable.setEmp_name(emp_name_et.getText().toString());
        companyTable.setAddress(address_et.getText().toString());
        companyTable.setAge(age_et.getText().toString());
        realm.commitTransaction();

    }

    @OnLongClick(R.id.insert_bt)
    boolean onInsertBtLongClick() {
        //TODO implement
        return true;
    }

    @OnClick(R.id.show_bt)
    void onShowBtClick() {
        RealmResults<CompanyTable> realmResults = realm.allObjects(CompanyTable.class);
        StringBuilder stringBuilder = new StringBuilder("");
        for (CompanyTable companyTable : realmResults) {
            //textview.setText("Address= " +companyTable.getAddress()+" Age= "+companyTable.getAge()+" Emp Name= "+ companyTable.getEmp_name());
            stringBuilder.append("Address= " + companyTable.getAddress() + " Age= " + companyTable.getAge() + " Emp Name= " + companyTable.getEmp_name());

        }
        textview.setText(stringBuilder);
    }

    @OnLongClick(R.id.show_bt)
    boolean onShowBtLongClick() {
        //TODO implement
        return true;
    }

    @OnClick(R.id.delete_bt)
    void onDeleteBtClick() {
       if (delete_ET.getText().toString().isEmpty()) {
            Toast.makeText(ActivityMainActivity.this, "Please enter the Employee Name, which record you want to delete ", Toast.LENGTH_SHORT).show();
            return;
        }

        RealmResults<CompanyTable> results = realm.where(CompanyTable.class).equalTo("emp_name", delete_ET.getText().toString().trim()).findAll();
        if(results.size()==0){
            Toast.makeText(ActivityMainActivity.this, "No Record found the employee name "+delete_ET.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        }
        realm.beginTransaction();
        results.clear();
        realm.commitTransaction();


    }

    @OnClick(R.id.search_result_bt)
    void onSearchClick(){
        if (search_ET.getText().toString().isEmpty()) {
            Toast.makeText(ActivityMainActivity.this, "Please enter the Employee Name, which record you want to search ", Toast.LENGTH_SHORT).show();
            return;
        }
        RealmResults<CompanyTable> realmResults = realm.where(CompanyTable.class).equalTo("emp_name", search_ET.getText().toString().trim()).findAll();
        if(realmResults.size()==0){
            Toast.makeText(ActivityMainActivity.this, "No Record found the employee name "+search_ET.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (CompanyTable companyTable : realmResults) {
            //textview.setText("Address= " +companyTable.getAddress()+" Age= "+companyTable.getAge()+" Emp Name= "+ companyTable.getEmp_name());
            stringBuilder.append("Address= " + companyTable.getAddress() + " Age= " + companyTable.getAge() + " Emp Name= " + companyTable.getEmp_name());

        }
        textview.setText(stringBuilder);

    }
    @OnLongClick(R.id.delete_bt)
    boolean onDeleteBtLongClick() {
        //TODO implement
        return true;
    }
}
