package hardik.invesmenthandle.com;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hardik.invesmenthandle.com.bankfd.sqlite.DatabaseHelper;

public class BankfdActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText bank,branch,branchno,principalamount,period,interest;
    Button btnsave;
    Button btnview;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankfd);

        myDB = new DatabaseHelper(this);
        bank = findViewById(R.id.bank);
        branch = findViewById(R.id.branch);
        branchno = findViewById(R.id.no);
        period = findViewById(R.id.period);
        principalamount = findViewById(R.id.principalamount);
        // date=findViewById(R.id.date);
        interest = findViewById(R.id.Roi);
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
                                                   (bank.getText() .toString(),
                                                           branch.getText() .toString(),
                                                           branchno.getText() .toString(),
                                                           period.getText().toString(),
                                                           principalamount.getText().toString(),
                                                           interest.getText().toString() );
                                           if (isInserted)
                                               Toast.makeText(BankfdActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                                           else
                                               Toast.makeText(BankfdActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();

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
                    buffer.append("BANK_NAME :").append(result.getString(0)).append("\n");
                    buffer.append("BRANCH_NAME :").append(result.getString(1)).append("\n");
                    buffer.append("BRANCH_NO :").append(result.getString(2)).append("\n");
                    buffer.append("PRINCIPLE_AMOUNT :").append(result.getString(3)).append("\n");
                    buffer.append("PERIOD :").append(result.getString(4)).append("\n");
                    buffer.append("INTEREST :").append(result.getString(5)).append("\n\n");


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
