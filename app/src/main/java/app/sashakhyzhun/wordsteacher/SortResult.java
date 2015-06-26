package app.sashakhyzhun.wordsteacher;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;

public class SortResult extends Activity {
    DBHelper dbHelper = new DBHelper(this);
    SQLiteDatabase db;
    RadioGroup rg;
    String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sortresult);
        rg = (RadioGroup) findViewById(R.id.rg);
        db = dbHelper.getWritableDatabase();
        populate();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch (id) {
                    case -1:
                        populate();
                        break;
                    case R.id.name:
                        orderBy = DBHelper.NAME;
                        populate(orderBy);
                        break;
                    case R.id.result:
                        orderBy = DBHelper.RESULT;
                        populate(orderBy);
                        break;
                    case R.id.id:
                        orderBy = DBHelper.KEY_ROWID;
                        populate(orderBy);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void populate() {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query(DBHelper.DATABASE_TABLE,null,null,null,null,null, DBHelper.KEY_ROWID);
        if (c!=null){
            c.moveToFirst();
        }
        String[] from = new String[] {DBHelper.KEY_ROWID, DBHelper.NAME, DBHelper.SURNAME, DBHelper.RESULT};
        int[] to = new int[] {R.id.id, R.id.name, R.id.surname, R.id.result};
        SimpleCursorAdapter mySimpleCursorAdapter;
        mySimpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_item_result, c, from, to,0);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(mySimpleCursorAdapter);
        dbHelper.close();
    }
    public void populate(String orderBy) {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query(DBHelper.DATABASE_TABLE, null, null, null, null, null, orderBy);
        if (c!=null){
            c.moveToFirst();
        }
        String[] from = new String[] {DBHelper.KEY_ROWID, DBHelper.NAME, DBHelper.SURNAME, DBHelper.RESULT};
        int[] to = new int[] {R.id.id, R.id.name, R.id.surname, R.id.result};
        SimpleCursorAdapter mySimpleCursorAdapter;
        mySimpleCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_item_result, c, from, to,0);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(mySimpleCursorAdapter);
        dbHelper.close();
    }

}
