package app.sashakhyzhun.wordsteacher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddNewQuestionActivity extends Activity {

    final String DIR = "Tests";
    final String FILENAME = "Tests.txt";

    ArrayList<String> strList = new ArrayList<>();

    EditText mQuestET;
    EditText mAnswer1ET;
    EditText mAnswer2ET;
    EditText mAnswer3ET;
    EditText mAnswer4ET;
    EditText mCorrectET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_question);
        mQuestET = (EditText)findViewById(R.id.questET);
        mAnswer1ET = (EditText)findViewById(R.id.answer1ET);
        mAnswer2ET = (EditText)findViewById(R.id.answer2ET);
        mAnswer3ET = (EditText)findViewById(R.id.answer3ET);
        mAnswer4ET = (EditText)findViewById(R.id.answer4ET);
        mCorrectET = (EditText)findViewById(R.id.correctAnswET);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                if ((!mQuestET.getText().equals("")) &&
                        (!mAnswer1ET.getText().toString().equals("")) &&
                        (!mAnswer2ET.getText().toString().equals("")) &&
                        (!mAnswer3ET.getText().toString().equals("")) &&
                        (!mAnswer4ET.getText().toString().equals("")) &&
                        (!mCorrectET.getText().toString().equals(""))) {
                    if ((Integer.valueOf(mCorrectET.getText().toString()) > 0) && (Integer.valueOf(mCorrectET.getText().toString()) < 5)) {
                        File path = new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/");
                        File file = new File(path, FILENAME);
                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                            bw.write(mQuestET.getText().toString() + ";" + mAnswer1ET.getText().toString() + ";"
                                    + mAnswer2ET.getText().toString() + ";" + mAnswer3ET.getText().toString() + ";"
                                    + mAnswer4ET.getText().toString() + ";" + mCorrectET.getText().toString() + "\n");
                            bw.close();
                            mQuestET.setText("");
                            mAnswer1ET.setText("");
                            mAnswer2ET.setText("");
                            mAnswer3ET.setText("");
                            mAnswer4ET.setText("");
                            mCorrectET.setText("");
                            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("Result", "FAIL");
                        }
                    }
                    else {
                        Toast.makeText(this, "Correct answer number should be in range of 1-4", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Not all fields was filled", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.removeLastBtn:
                Button removeBtn = (Button)findViewById(R.id.removeLastBtn);
                removeBtn.setEnabled(false);
                RemoveLast();
                removeBtn.setEnabled(true);
                Toast.makeText(this, "Deleted, bro", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void RemoveLast () {
        File path = new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/");
        File file = new File(path, FILENAME);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            while ((str = br.readLine()) != null) {
                strList.add(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Result", "FAIL");
        }
        if (strList.size() > 0) {
            strList.remove(strList.size() - 1);
        }
        else {
            Toast.makeText(this, "The file is already empty", Toast.LENGTH_SHORT).show();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            for (String s: strList) {
                bw.write(s + "\n");
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.d("Result", "FAIL");
        }
        strList.clear();
    }
}