package app.sashakhyzhun.wordsteacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity {

    SharedPreferences sPref;
    Button login;
    EditText name, surname;

    @Override // Called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
    }


    @Override //отвечает за создание меню "info"
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void login(View v) {
        String fName = name.getText().toString();
        String fSurname = surname.getText().toString();
        sPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sPref.edit();
        e.putString("user_name", fName+ " " + fSurname);
        e.commit();

        Intent main = new Intent(getApplicationContext(),AndroidListViewActivity.class);
        startActivity(main);
    }
}
