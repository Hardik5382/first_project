
package hardik.invesmenthandle.com;

import android.content.Context;
import android.content.Intent;
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
    private Context context;

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

        context = this;
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

                Intent i = new Intent(context,SavedPolicyList.class);
                context.startActivity(i);
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
