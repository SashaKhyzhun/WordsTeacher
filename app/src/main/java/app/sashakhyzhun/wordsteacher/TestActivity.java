package app.sashakhyzhun.wordsteacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {

    final String LOG_TAG = "myLogs";

    private String[][] str;
    private int id = 1;
    private int correctAnswerId;
    private int chosenAnswerId = -1;
    private int questionCount;
    private int score = 0;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        Refresh(id - 1);

    }

    public void onClick (View v){
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                chosenAnswerId = 1;
                Log.d(LOG_TAG, "Answer number one");
                break;
            case R.id.radioButton2:
                chosenAnswerId = 2;
                Log.d(LOG_TAG, "Answer number two");
                break;
            case R.id.radioButton3:
                chosenAnswerId = 3;
                Log.d(LOG_TAG, "Answer number three");
                break;
            case R.id.radioButton4:
                chosenAnswerId = 4;
                Log.d(LOG_TAG, "Answer number four");
                break;
        }
        if (chosenAnswerId == correctAnswerId){
            score++;
            Log.d(LOG_TAG, chosenAnswerId + " " + correctAnswerId + " Correct");
            Log.d(LOG_TAG, score + " Score");
        }
        else {
            Log.d(LOG_TAG, chosenAnswerId + " " + correctAnswerId + " Incorrect");
        }
        if (id < str.length) {
            if(radioGroup.getCheckedRadioButtonId() != -1 ) {
                Refresh(id);
                id++;
            }
            else {
                Toast.makeText(this, "Select answer!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("questCount", questionCount);
            startActivity(intent);
            Log.d(LOG_TAG, "End of list");
            Log.d(LOG_TAG, score + " Score");
            this.finish();
        }
    }

    public void Refresh(int i){
        try {
            Reader reader = new Reader();
            str = reader.Read();
            if (str.length > 0) {
            ((TextView) findViewById(R.id.txtView)).setText(str[i][0]);
            ((Button) findViewById(R.id.radioButton)).setText(str[i][1]);
            ((Button) findViewById(R.id.radioButton2)).setText(str[i][2]);
            ((Button) findViewById(R.id.radioButton3)).setText(str[i][3]);
            ((Button) findViewById(R.id.radioButton4)).setText(str[i][4]);
            correctAnswerId = Integer.parseInt(str[i][5]);
            questionCount = str.length;
            radioGroup.clearCheck();
            Log.d(LOG_TAG, correctAnswerId + " Correct Answer ID");
        } else {
                Toast.makeText(this, "There is no tests. Please add some", Toast.LENGTH_SHORT).show();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            Log.d("Refresh", "Check your file");
        }
    }

    @Override //параметры меню "инфо"
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent addNewQuestion = new Intent(this, AddNewQuestionActivity.class);
        startActivity(addNewQuestion);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}