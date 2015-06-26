package app.sashakhyzhun.wordsteacher;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static app.sashakhyzhun.wordsteacher.R.id.aboutButton;
import static app.sashakhyzhun.wordsteacher.R.id.exitButton;
import static app.sashakhyzhun.wordsteacher.R.id.feedBackButton;
import static app.sashakhyzhun.wordsteacher.R.id.goToMainMenu;
import static app.sashakhyzhun.wordsteacher.R.id.onlineTranslate;
import static app.sashakhyzhun.wordsteacher.R.id.startButton;


public class MainActivity extends Activity {


    @Override // Called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case startButton:
                Intent TestActivity = new Intent(this, TestActivity.class);
                TestActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(TestActivity);
                break;
            case onlineTranslate:
                Intent onlineTranslate = new Intent(this, TranslateActivity.class);
                onlineTranslate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(onlineTranslate);
                break;
            case aboutButton:
                Intent AboutActivity = new Intent(this, AboutActivity.class);
                AboutActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(AboutActivity);
                break;
            case exitButton:
                moveTaskToBack(true);
                super.onDestroy();
                System.exit(0);
                break;
            case goToMainMenu:
                Intent MainActivity = new Intent(this, MainActivity.class);
                startActivity(MainActivity);
                finish();
                break;
            case feedBackButton:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sasha6427@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "feedback from user :)");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


    public void audioOnClick() {
        // создаём новый объект mediaPlayer
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.mipmap.clicksound);
        mediaPlayer.start(); // запускаем воспроизведение
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
