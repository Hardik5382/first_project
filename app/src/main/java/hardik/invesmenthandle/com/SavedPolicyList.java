package hardik.invesmenthandle.com;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import hardik.invesmenthandle.com.adapters.adapter;
import hardik.invesmenthandle.com.modal.PolicyAcivity;
import hardik.invesmenthandle.com.policy.sqlite.helper.DatabaseHelper;

public class SavedPolicyList extends AppCompatActivity {
    private DatabaseHelper myDB;

    private RecyclerView recyclerView;
    private List<PolicyAcivity> policyAcivities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_policy_list);

        policyAcivities = new ArrayList<>();




        recyclerView = findViewById(R.id.policy_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        previous code
        myDB = new DatabaseHelper(this);

        Cursor result = myDB.getAllData();
        if (result.getCount() == 0 ){
            //show message
            showMessage("ERROR","NO DATA FOUND");
            return;
        }else {

            StringBuilder buffer = new StringBuilder();
            while (result.moveToNext()) {
                StringBuilder test = buffer.append("PNO :").append(result.getString(0)).append("\n");
                buffer.append("POLICY_NAME :").append(result.getString(1)).append("\n");
                buffer.append("PLAN_NO :").append(result.getString(2)).append("\n");
                buffer.append("PLAN_NAME :").append(result.getString(3)).append("\n");
                buffer.append("SUM_ASSURED :").append(result.getString(4)).append("\n");
                buffer.append("PREMIUM_AMOUNT :").append(result.getString(5)).append("\n");
                buffer.append("YEARS :").append(result.getString(6)).append("\n\n");

                PolicyAcivity policyAcivity = new PolicyAcivity(String.valueOf(test),"2","asdfasd","12312","12312");
                policyAcivities.add(policyAcivity);
            }
            RecyclerView.Adapter adapter = new adapter(policyAcivities);
            recyclerView.setAdapter(adapter);
            //show all
//            showMessage("Data", buffer.toString());
            return;
        }




    }

    public void  showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
