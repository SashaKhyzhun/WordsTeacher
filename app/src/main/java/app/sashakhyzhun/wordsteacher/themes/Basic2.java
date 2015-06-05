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


public class Basic2 extends MainActivity {

    public int question_counter = 0;
    public int answer_counter = 0;
    public static int wrong, correct;

    Button btnSubmit;
    TextView txtQuestion;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;

    String[] QUESTIONS = {
            "1. Виберіть переклад слова 'газета'",
            "2. Виберіть переклад слова 'мужчини'",
            "3. Перекладіть текст 'i read' ",
            "4. Перекладіть текст 'we read'",
            "5. Перекладіть фразу 'The boys read a newspaper'",
            "6. Перекладіть фразу 'The girls and the boys read'",
            "7. Виберіть правильний переклад 'Ці мужчини п'ють'?",
            "8. Перекладіть текст 'The women and the girls read'",
            "9. Виберіть правильний переклад 'Ці хлопці п'ють",
            "10. Як перекладається слово 'газета'?",
    };
    double amount_of_questions = QUESTIONS.length;

    String[] correctANSWERS = {
            "a newspaper",
            "men",
            "я читаю",
            "ми читаєм",
            "хлопці читають газету",
            "дівчата та хлопці читають",
            "The men drink",
            "жінки і дівчата читають",
            "The boys drink",
            "A newspaper",
    };
    String[] userANSWERS = {};
    String[] ANSWERS = {
            "a sandwich", "women", "a newspaper", "an apple",
            "men", "man", "a cat", "girls",
            "я пишу", "я їм", "я п'ю", "я читаю",
            "ми читаєм", "ми п'єм", "ми думаєм", "дача",
            "дівчата читають газету", "хлопці читають газету", "хлопець читають газету", "кіт читає газету",
            "кіт не читає", "дівчата читають", "хлопці читають", "дівчата та хлопці читають",
            "Those women drink", "The men drink", "These girls are drinking.", "These man are drinking",
            "Жінки п'ють", "Жінки і дівчата п'ють", "Жінки читають", "Жінки і дівчата читають",
            "The children drink", "These boys eat", "The boys drink", "The boys read a newspaper'",
            "A menu", "A newspaper", "an apple", "A newspaper",

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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton userAnswer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                //перевіряєм чи вибрали відповідь, якщо нет, то вивиодить TOAST
                if (radioGroup.getCheckedRadioButtonId() == - 1) {
                    //Toast toast = new Toast(TestActivity.this);
                    Toast.makeText(Basic2.this, "Виберіть відповідь", Toast.LENGTH_SHORT).show();
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
                        Intent ResultActivity = new Intent(Basic2.this, app.sashakhyzhun.wordsteacher.ResultActivity.class);
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
