package hardik.invesmenthandle.com.policy.sqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Policy.db";
    public static  final String TABLE_NAME = "Policy_table";
    public static  final String COL_1 = "PNO";
    public static  final String COL_2 = "POLICY_NAME";
    public static  final String COL_3 = "PLAN_NO";
    public static  final String COL_4 = "PLAN_NAME";
    public static  final String COL_5 = "SUM_ASSURED";
    public static  final String COL_6 = "PREMIUM_AMOUNT";
    public static  final String COL_7 = "YEARS";


    public DatabaseHelper( Context context ) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Policy_table (PNO INTEGER PRIMARY KEY AUTOINCREMENT,POLICY_NAME TEXT,PLAN_NO INTEGER,PLAN_NAME TEXT, SUM_ASSURED INTEGER,PREMIUM_AMOUNT INTEGER,YEARS DATE )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String POLICY_NAME,String PLAN_NO,String PLAN_NAME,String SUM_ASSURED,String PREMIUM_AMOUNT,String YEARS){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,POLICY_NAME);
        contentValues.put(COL_3,PLAN_NO);
        contentValues.put(COL_4,PLAN_NAME);
        contentValues.put(COL_5,SUM_ASSURED);
        contentValues.put(COL_6,PREMIUM_AMOUNT);
        contentValues.put(COL_7,YEARS);
        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != 1;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ TABLE_NAME,null);
        return result;
    }
    public boolean UpdateData(String PNO, String POLICY_NAME, String PLAN_NO, String PLAN_NAME, String SUM_ASSURED, String PREMIUM_AMOUNT, String YEARS,String DATE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,PNO);
        contentValues.put(COL_2,POLICY_NAME);
        contentValues.put(COL_3,PLAN_NO);
        contentValues.put(COL_4,PLAN_NAME);
        contentValues.put(COL_5,SUM_ASSURED);
        contentValues.put(COL_6,PREMIUM_AMOUNT);
        contentValues.put(COL_7,YEARS);

        db.update(TABLE_NAME,contentValues,"pno = ?",new String[] {PNO});
        return true;
    }
}
