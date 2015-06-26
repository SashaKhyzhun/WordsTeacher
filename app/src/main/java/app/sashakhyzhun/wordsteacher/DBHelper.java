package app.sashakhyzhun.wordsteacher;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "data.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "results_table";

    public static final String KEY_ROWID = "_id";
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String RESULT = "RESULT";

    //"(_id INTEGER PRIMARY KEY AUTOINCREMENT,

    private static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE "
            + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
            + " TEXT NOT NULL, " + RESULT + " NUM, " + SURNAME  + " TEXT NOT NULL" + ");";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MYLOGS", "--- onCreate database ---");

        db.execSQL(DATABASE_CREATE_SCRIPT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
        onCreate(db);
    }

}