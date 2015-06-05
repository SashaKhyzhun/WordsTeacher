package app.sashakhyzhun.wordsteacher;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends Activity {


    TextView txtResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = (TextView) findViewById(R.id.tvView);


        //get data from TestActivity
        Bundle bundle = getIntent().getExtras();
        String fName = bundle.getString("name");
        String fSurname = bundle.getString("surname");

        int correct = bundle.getInt("correct_answers");
        int wrong = 10-correct;



        txtResult.setText("Вітаємо, Ви пройшли тест!\n\n Ваш результат такий:" +
                "\n Правильних: " + correct + "\n Непрвильних: " + wrong);
    }



    @Override  //параметры меню "инфо"
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.feedBack) {
            String appPackageName= getPackageName();
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/u/0/#drafts?compose=14d29e139700bea6" + appPackageName));
            marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(marketIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

/*    TextView txtResult;
    TextView tvView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtResult = (TextView) findViewById(R.id.result);


        Intent intent = getIntent();

        String fName = intent.getStringExtra("fname");
        String lName = intent.getStringExtra("lname");

        //get data from TestActivity
        Bundle bundle = getIntent().getExtras();
        String uname = bundle.getString("name");
        int correct = bundle.getInt("correct_answers");
        int wrong = 10-correct;

        txtResult.setText("Вітаємо," + uname + "ви пройшли тест!\n\n Ваш результат такий:" + "\n Правильних: " + correct + "\n Непрвильних: " + wrong);
    }



    @Override  //параметры меню "инфо"
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.feedBack) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sasha6427@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "feedback from user :)");
            i.putExtra(Intent.EXTRA_TEXT, "");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }*/


