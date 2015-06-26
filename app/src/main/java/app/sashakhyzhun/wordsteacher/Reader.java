package app.sashakhyzhun.wordsteacher;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    final String DIR = "Tests";
    final String FILENAME = "Tests.txt";
    final String LOG_TAG = "myLogs";

    public String[][] Read() {
        ArrayList<String[]> bufferString = new ArrayList<>();
        File path = new File(Environment.getExternalStorageDirectory() + "/" + DIR + "/");
        File file = new File(path, FILENAME);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                bufferString.add(str.split(";"));
                Log.d(LOG_TAG, str);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "FAIL");
        }
        return bufferString.toArray(new String[bufferString.size()][]);
    }
}