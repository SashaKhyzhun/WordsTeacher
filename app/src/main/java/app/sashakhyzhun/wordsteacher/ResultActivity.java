package app.sashakhyzhun.wordsteacher;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ResultActivity extends Activity {

    TextView txtResult;
    SQLiteDatabase db;
    DBHelper dbHelper = new DBHelper(this);
    final String DIR = "Tests";
    final String FILENAME = "Results.txt";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = (TextView) findViewById(R.id.tvView);
        int score = getIntent().getIntExtra("score", 0);
        int questCount = getIntent().getIntExtra("questCount", 0);
        ((TextView) findViewById(R.id.tvView)).setText("Ваш результат: " + score + "/" + questCount);
        saveResult(score, questCount);

        //ggwp
        SharedPreferences mSharedPreference = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userName = mSharedPreference.getString("user_name", "");
        String userSurname = mSharedPreference.getString("user_surname", "");

        ContentValues cv = new ContentValues();
        db = dbHelper.getWritableDatabase();

        cv.put(DBHelper.NAME, userName);
        cv.put(DBHelper.RESULT, score);
        cv.put(DBHelper.SURNAME, userSurname);
        db.insert(DBHelper.DATABASE_TABLE, null, cv);
        float rate = Float.valueOf(score) / Float.valueOf(questCount);
        String strRate = "";
        if (rate >= 0.9) {
            strRate = "Excellent!";
        }
        else if ((rate < 0.9) && (rate >= 0.74)) {
            strRate = "Good!";
        }
        else if ((rate < 0.74)&&(rate >= 0.6)) {
            strRate = "Normal!";
        }
        else {
            strRate = "Bad!";
        }
        txtResult.setText("Congratulations, " + userName + "! you made it!\n\n Your score:" +
                "\n Correct: " + score + "\n Wrong: " + (questCount - score) + "\n\n " +
                "Your rating: " + strRate);
    }


    private void saveResult(int score, int count) {
        int id = 0;

        File path = new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/");
        File file = new File(path, FILENAME);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String s;
            while ((str = br.readLine()) != null) {
                s = str.split(" ")[0];
                id = Integer.valueOf(s);
                Log.d("Result", str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Result", "FAIL");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            id++;
            bw.write(id + " Your score: " + score + "/" + count + "\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Result", "FAIL");
        }
    }


    public void resultClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu:
               /* Intent main_menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main_menu);*/
                finish();
                break;
            case R.id.results:
                Intent list = new Intent(getApplicationContext(), SortResult.class);
                startActivity(list);
                finish();
                break;
            case R.id.new_test:
                Intent test = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(test);
                finish();
                break;
            default: break;
        }
    }


    @Override  //параметры меню "инфо"
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

}

