package fr.be2.monapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SQLHelper2 extends SQLiteOpenHelper {
        private static final String DB_NAME = "GSB.db";
        private static final String DB_TABLE = "Visiteur";
        public static final String KEY_Id = "Id";
        public static final String KEY_Nom= "Nom";
        public static final String KEY_Prenom = "Prenom";
        public static final String KEY_Adresse = "Adresse";
        public static final String KEY_Code_Postal = "Code Postal";
        public static final String KEY_Ville = "Ville";
        public static final String KEY_Email = "Email";
        public static final String KEY_Tel = "Tel";

        private static final String TAG = "SQLHelper";
        private fr.be2.monapplication.SQLHelper2 dbHelper2;
        private SQLiteDatabase db;


        private static final String CREATE_TABLE = "CREATE TABLE Visiteur (Id INTEGER PRIMARY KEY AUTOINCREMENT,Nom TEXT,Prenom TEXT ,Adresse TEXT,Code_Postal INTEGER ,Ville TEXT,Email TEXT ,Tel INTEGER )";
        public SQLHelper2(Context context) {
            super(context,DB_NAME,null,1);
        }




    public fr.be2.monapplication.SQLHelper2 open() throws SQLException {
            SQLiteDatabase db = this.getReadableDatabase();
            return this;
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(sqLiteDatabase);
        }

        public boolean insertData(String Nom , String Prenom, String Adresse , Integer Code_Postal , String Ville , String Email , String Tel){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_Nom, Nom);
            contentValues.put(KEY_Prenom , Prenom);
            contentValues.put(KEY_Adresse, Adresse);
            contentValues.put(KEY_Code_Postal, Code_Postal);
            contentValues.put(KEY_Ville, Ville);
            contentValues.put(KEY_Email, Email);
            contentValues.put(KEY_Tel, Tel);
            long result = db.insert(DB_TABLE, null, contentValues);
            return result != -1;

        }

        public Cursor fetchAllCountries() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor mCursor = db.query(DB_TABLE, new String[] {"rowid _id",KEY_Nom, KEY_Prenom, KEY_Adresse, KEY_Code_Postal, KEY_Ville,KEY_Email,KEY_Tel },
                    null, null, null, null, null);

            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor;
        }

        public Cursor viewData() {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "select * from " + DB_TABLE;
            Cursor pointeur = db.rawQuery(query, null);
            return pointeur;

        }

        public boolean deleteData(Integer Id) {
            SQLiteDatabase db = this.getWritableDatabase();

            long result = db.delete(DB_TABLE, "Id=" + Id, null);

            return result != -1;

        }

}
