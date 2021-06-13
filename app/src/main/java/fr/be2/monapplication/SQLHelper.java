package fr.be2.monapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

public class SQLHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "GSB.db";
        private static final String DB_TABLE = "NoteDeForfait";
        public static final String KEY_ID = "ID";
        public static final String KEY_LIBELLE= "LIBELLE";
        public static final String KEY_MONTANT = "MONTANT";
        public static final String KEY_DATEDESAISIT = "DATEDESAISIT";
        public static final String KEY_DATEDEFRAIS = "DATEDEFRAIS";
        public static final String KEY_QUANTITE = "QUANTITE";
        public static final String KEY_TYPEDEFRAIS = "TYPEDEFRAIS";


        private static final String TAG = "SQLHelper";
        private SQLHelper dbHelper;
        private SQLiteDatabase db;

        // private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +" ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT" + ")";
        private static final String CREATE_TABLE = "CREATE TABLE NoteDeForfait (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPEDEFRAIS TEXT, LIBELLE TEXT, QUANTITE INTEGER, MONTANT FLOAT,DATEDEFRAIS TEXT, DATEDESAISIT DATETIME DEFAULT CURRENT_TIMESTAMP)";
        public SQLHelper(Context context){
            super(context,DB_NAME,null,1);
        }

        public SQLHelper open() throws SQLException {
           // mDbHelper = new SQLHelper(mCtx);
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

        public boolean insertData(String typefrais, String libelle, Integer quantite, Double montant, String date){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_DATEDEFRAIS,typefrais);
            contentValues.put("LIBELLE", libelle);
            contentValues.put("QUANTITE", quantite);
            contentValues.put(KEY_MONTANT, montant);
            contentValues.put(KEY_DATEDEFRAIS, date);
            long result = db.insert(DB_TABLE, null, contentValues);
            return result != -1;

        }

        public Cursor fetchAllCountries() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor mCursor = db.query(DB_TABLE, new String[] {"rowid _id",KEY_ID,
                            KEY_LIBELLE, KEY_MONTANT, KEY_DATEDESAISIT, KEY_DATEDEFRAIS, KEY_QUANTITE,KEY_TYPEDEFRAIS},
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

        public boolean deleteData(Integer ID) {
            SQLiteDatabase db = this.getWritableDatabase();

            long result = db.delete(DB_TABLE, "ID=" + ID, null);

            return result != -1;

        }

        public Cursor fetchByDate (String inputText) throws SQLException {
            Log.w(TAG, inputText);
            Cursor mCursor = null;
            if (inputText == null  ||  inputText.length () == 0)  {
                mCursor = db.query(DB_TABLE, new String[] {KEY_DATEDEFRAIS},
                    null, null, null, null, null);

            }
            else {
                mCursor = db.query(true, DB_TABLE, new String[] {KEY_DATEDEFRAIS},
                        KEY_DATEDEFRAIS + " like '%" + inputText + "'", null,
                        null, null, null, null);
            }
            if (mCursor != null) {
                mCursor.moveToFirst();
            }return mCursor;

    }
    }
