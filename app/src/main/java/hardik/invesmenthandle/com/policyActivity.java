
package hardik.invesmenthandle.com;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hardik.invesmenthandle.com.policy.sqlite.helper.DatabaseHelper;

public class policyActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText polnum,planno,plan,sumassured,premiumamount,terms;
    Button btnsave;
    Button btnview;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        myDB = new DatabaseHelper(this);
        polnum = findViewById(R.id.polnum);
        planno = findViewById(R.id.planno);
        plan = findViewById(R.id.plan);
        sumassured = findViewById(R.id.sumassured);
        premiumamount = findViewById(R.id.premiumamount);
       // date=findViewById(R.id.date);
        terms = findViewById(R.id.terms);
        btnsave = findViewById(R.id.Save);
        btnview = findViewById(R.id.View);
        btnUpdate = findViewById(R.id.Update);
        btnsave();
        btnview();
    }

    private void btnsave() {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData
                        (polnum.getText() .toString(),
                        planno.getText() .toString(),
                        plan.getText() .toString(),
                        sumassured.getText().toString(),
                        premiumamount.getText().toString(),
                        terms.getText().toString() );
                if (isInserted)
                    Toast.makeText(policyActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(policyActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();

            }
        }
        );

    }
    private void btnview(){
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = myDB.getAllData();
                if (result.getCount() == 0 ){
                    //show message
                    showMessage("ERROR","NO DATA FOUND");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (result.moveToNext()){
                    buffer.append("PNO :").append(result.getString(0)).append("\n");
                    buffer.append("POLICY_NAME :").append(result.getString(1)).append("\n");
                    buffer.append("PLAN_NO :").append(result.getString(2)).append("\n");
                    buffer.append("PLAN_NAME :").append(result.getString(3)).append("\n");
                    buffer.append("SUM_ASSURED :").append(result.getString(4)).append("\n");
                    buffer.append("PREMIUM_AMOUNT :").append(result.getString(5)).append("\n");
                    buffer.append("YEARS :").append(result.getString(6)).append("\n\n");

                }
                //show all
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void  showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
