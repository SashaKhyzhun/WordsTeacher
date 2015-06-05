package app.sashakhyzhun.wordsteacher.themes;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.sashakhyzhun.wordsteacher.MainActivity;
import app.sashakhyzhun.wordsteacher.R;
import app.sashakhyzhun.wordsteacher.ResultActivity;

public class Basic extends MainActivity {

    public int question_counter = 0;
    public int answer_counter = 0;
    public static int wrong, correct;

    Button btnSubmit;
    TextView txtQuestion;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;

    String[] QUESTIONS = {
            "1. Виберіть переклад слова 'хлопець'",
            "2. Виберіть переклад слова 'дівчина'",
            "3. Як перекладіть 'I eat'?",
            "4. Перекладіть текст 'She is a girl'",
            "5. Як перекладається 'Is he a man?'",
            "6. Виберіть переклад слова 'вода'",
            "7. Виберіть переклад слова 'хліб'",
            "8. Перекладіть текст 'I drink'",
            "9. Перекладіть текст 'She eats",
            "10. Як перекладається 'The boy drinks water.",

    };
    double amount_of_questions = QUESTIONS.length;

    String[] correctANSWERS = {
            "a boy",
            "a girl",
            "я їм",
            "вона дівчина",
            "він мужчина?",
            "water",
            "bread",
            "я п'ю",
            "вона їсть",
            "хлопець п'є воду",
    };
    String[] userANSWERS = {};
    String[] ANSWERS = {
            "a boy", "an apple", "a girl", "a woman",
            "a woman", "a girl", "an apple", "a cat",
            "я дівчина", "я п'ю", "я їм", "я кіт",
            "вона дівчина", "він хлопець", "вона жінка", "він чоловік",
            "він мужчина!", "він кіт?", "він хлопець?", "він мужчина?",
            "woman", "bread", "water", "man",
            "milk", "water", "paper", "bread",
            "я п'ю", "я ї'м", "я незнаю", "я дівчина",
            "він їсть", "я ї'м", "вона їсть", "він п'є",
            "хлопець п'є воду", "дівчина п'є воду", "хлопець їсть м'ясо", "вона п'є",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btnSubmit = (Button) findViewById(R.id.button_submit);
        txtQuestion = (TextView) findViewById(R.id.txtView);
        txtQuestion.setText(QUESTIONS[question_counter]);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        rb1.setText(ANSWERS[answer_counter]);
        rb2.setText(ANSWERS[answer_counter+1]);
        rb3.setText(ANSWERS[answer_counter+2]);
        rb4.setText(ANSWERS[answer_counter+3]);

        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton userAnswer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                //перевіряєм чи вибрали відповідь, якщо нет, то вивиодить TOAST
                if (radioGroup.getCheckedRadioButtonId() == - 1) {
                    //Toast toast = new Toast(TestActivity.this);
                    Toast.makeText(Basic.this, "Виберіть відповідь", Toast.LENGTH_SHORT).show();
                }
                else {
                    String answerText = userAnswer.getText().toString();
                    if (answerText.equalsIgnoreCase(correctANSWERS[question_counter]))
                    {
                        correct++;
                        audioOnClick();
                    }
                    else {
                        wrong++;
                        audioOnClick();
                    }
                    question_counter++;

                    //clear radioGroup
                    radioGroup.clearCheck();

                    if (question_counter < QUESTIONS.length) {
                        txtQuestion.setText(QUESTIONS[question_counter]);
                        rb1.setText(ANSWERS[question_counter*4]);
                        rb2.setText(ANSWERS[question_counter*4+1]);
                        rb3.setText(ANSWERS[question_counter*4+2]);
                        rb4.setText(ANSWERS[question_counter*4+3]);
                    }
                    else {
                        finish();
                        //get data from DataActivity
                        Intent myIntent = getIntent();
                        String username = myIntent.getStringExtra("nameString");
                        String usergroup = myIntent.getStringExtra("groupString");

                        //move to ResultActivity
                        Intent ResultActivity = new Intent(Basic.this, ResultActivity.class);
                        ResultActivity.putExtra("correct_answers", correct);
                        ResultActivity.putExtra("name", username);
                        ResultActivity.putExtra("group", usergroup);
                        startActivity(ResultActivity);

                        //clear answers
                        correct = 0;
                        wrong = 0;
                    }
                }
            }
        });
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
          //  Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "group text"
                    + "result text" + ");");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

}

