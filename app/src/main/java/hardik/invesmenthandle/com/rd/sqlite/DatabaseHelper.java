package hardik.invesmenthandle.com.rd.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Rd.db";
    public static  final String TABLE_NAME = "Rd_table";
    public static  final String COL_1 = "DEPOSIT_NUMBER";
    public static  final String COL_2 = "BANK_NAME";
    public static  final String COL_3 = "AMOUNT";
    public static  final String COL_4 = "DATE";
    public static  final String COL_5 = "TERMS";
    public static  final String COL_6 = "INTEREST";



    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bankfd_table (DEPOSIT_NUMBER INTEGER PRIMARY KEY , BANK_NAME TEXT ,AMOUNT INTEGER ,DATE TEXT, TERMS INTEGER,INTEREST INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String DEPOSIT_NUMBER,String BANK_NAME,String AMOUNT,String DATE,String TERMS,String INTEREST){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,DEPOSIT_NUMBER);
        contentValues.put(COL_2,BANK_NAME);
        contentValues.put(COL_3,AMOUNT);
        contentValues.put(COL_4,DATE);
        contentValues.put(COL_5,TERMS);
        contentValues.put(COL_6,INTEREST);

        long result = db.insert(TABLE_NAME,null,contentValues);
        return result != 1;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+ TABLE_NAME,null);
        return result;
    }
    public boolean UpdateData(String DEPOSIT_NUMBER,String BANK_NAME,String AMOUNT,String DATE,String TERMS,String INTEREST){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,DEPOSIT_NUMBER);
        contentValues.put(COL_2,BANK_NAME);
        contentValues.put(COL_3,AMOUNT);
        contentValues.put(COL_4,DATE);
        contentValues.put(COL_5,TERMS);
        contentValues.put(COL_6,INTEREST);

        db.update(TABLE_NAME,contentValues,"deposit no = ?",new String[] {DEPOSIT_NUMBER});
        return true;
    }
}
