package hardik.invesmenthandle.com;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hardik.invesmenthandle.com.rd.sqlite.DatabaseHelper;

public class RdActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText dno,bname,amount,date,terms,interest;
    Button btnsave;
    Button btnview;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rd);

        myDB = new DatabaseHelper(this);
        dno = findViewById(R.id.dno);
        bname = findViewById(R.id.bname);
        amount = findViewById(R.id.amount);
        date = findViewById(R.id.date);


        interest = findViewById(R.id.interest);
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
                        (dno.getText() .toString(),
                                bname.getText() .toString(),
                                amount.getText() .toString(),
                                date.getText().toString(),
                                terms.getText().toString(),
                                interest.getText().toString() );
                if (isInserted)
                    Toast.makeText(RdActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(RdActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();

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
                    buffer.append("DEPOSIT_NUMBER :").append(result.getString(0)).append("\n");
                    buffer.append("BANK_NAME :").append(result.getString(1)).append("\n");
                    buffer.append("AMOUNT :").append(result.getString(2)).append("\n");
                    buffer.append("DATE :").append(result.getString(3)).append("\n");
                    buffer.append("TERMS :").append(result.getString(4)).append("\n");
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

