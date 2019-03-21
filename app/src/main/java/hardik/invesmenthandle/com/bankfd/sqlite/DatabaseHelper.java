package hardik.invesmenthandle.com.bankfd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bankfd.db";
    public static  final String TABLE_NAME = "bankfd_table";
    public static  final String COL_1 = "BANK_NAME";
    public static  final String COL_2 = "BRANCH_NAME";
    public static  final String COL_3 = "BRANCH_NO";
    public static  final String COL_4 = "PRINCIPLE_AMOUNT";
    public static  final String COL_5 = "PERIOD";
    public static  final String COL_6 = "INTEREST";



    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bankfd_table (BANK_NAME TEXT ,BRANCH_NAME TEXT,BRANCH_NO INTEGER PRIMARY KEY AUTOINCREMENT,PRINCIPLE_AMOUNT TEXT, PERIOD INTEGER,INTEREST INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String BANK_NAME,String BRANCH_NAME,String BRANCH_NO,String PRINCIPLE_AMOUNT,String PERIOD,String INTEREST){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,BANK_NAME);
        contentValues.put(COL_2,BRANCH_NAME);
        contentValues.put(COL_3,BRANCH_NO);
        contentValues.put(COL_4,PRINCIPLE_AMOUNT);
        contentValues.put(COL_5,PERIOD);
        contentValues.put(COL_6,INTEREST);

        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != 1;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ TABLE_NAME,null);
        return result;
    }
    public boolean UpdateData(String BANK_NAME,String BRANCH_NAME,String BRANCH_NO,String PRINCIPLE_AMOUNT,String PERIOD,String INTEREST){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,BANK_NAME);
        contentValues.put(COL_2,BRANCH_NAME);
        contentValues.put(COL_3,BRANCH_NO);
        contentValues.put(COL_4,PRINCIPLE_AMOUNT);
        contentValues.put(COL_5,PERIOD);
        contentValues.put(COL_6,INTEREST);

        db.update(TABLE_NAME,contentValues,"branch no = ?",new String[] {BRANCH_NO});
        return true;
    }
}
