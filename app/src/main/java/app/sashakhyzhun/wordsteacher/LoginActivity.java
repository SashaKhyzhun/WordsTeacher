package app.sashakhyzhun.wordsteacher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class LoginActivity extends ActionBarActivity {

    SharedPreferences sPref;
    Button login;
    EditText name, surname;

    final String DIR = "Tests";
    final String FILENAME = "Tests.txt";

    @Override // Called when the activity is first created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        File path = new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/");
        File file = new File(path, FILENAME);

        if (!file.exists()) { //якшо ні файла нема
            if (!path.exists()) { //ні папки
                new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/").mkdir(); //то создає папку
            }
            Log.d("File", "File does not exist");
            //Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
            try {
                new File(path, FILENAME).createNewFile(); //і файл
            } catch (IOException e) {
                e.printStackTrace();
            }
            AssetManager assetManager = getAssets();
            InputStream input;
            try {
                input = assetManager.open("Tests.txt");
                int size = input.available();
                byte[] buffer = new byte[size];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                bw.write(text); //тут записує у файл
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            //Toast.makeText(this, "File does exist", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View v) {
        String fName = name.getText().toString();
        String fSurname = surname.getText().toString();
        sPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sPref.edit();
        e.putString("user_name", fName);
        e.putString("user_surname", fSurname);
        e.commit();

        Intent main = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(main);
        finish();
    }
}