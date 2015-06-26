package app.sashakhyzhun.wordsteacher;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

public class AboutActivity extends MainActivity {

    Button goToMainMenu;

    @Override // Called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        goToMainMenu = (Button) findViewById(R.id.goToMainMenu);

    }


    @Override //параметры меню "инфо"
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

}
