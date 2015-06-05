package app.sashakhyzhun.wordsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import app.sashakhyzhun.wordsteacher.themes.Basic;
import app.sashakhyzhun.wordsteacher.themes.Basic2;
import app.sashakhyzhun.wordsteacher.themes.Eat;
import app.sashakhyzhun.wordsteacher.themes.Phrases;

public class AndroidListViewActivity extends MainActivity {


    final String LOG_TAG = "myLogs";
    ListView ListView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);


        //создаем лист с темами
        ListView = (ListView) findViewById(R.id.list_themes);
        //создаем адаптер
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.themes, android.R.layout.simple_list_item_1);
        //присваиваем адаптер
        ListView.setAdapter(adapter);
        //добавляем клик метод
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: Intent i0 = new Intent(getApplicationContext(), Basic.class);
                        startActivity(i0);
                        audioOnClick();
                        break;
                    case 1: Intent i1 = new Intent(getApplicationContext(), Basic2.class);
                        startActivity(i1);
                        audioOnClick();
                        break;
                    case 2: Intent i2 = new Intent(getApplicationContext(), Phrases.class);
                        startActivity(i2);
                        audioOnClick();
                        break;
                    case 3: Intent i3 = new Intent(getApplicationContext(), Eat.class);
                        startActivity(i3);
                        audioOnClick();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Цей розділ вийде в наступній версії", Toast.LENGTH_SHORT).show();
                        audioOnClick();
                }
            }

        });


        //Создаем скролл
        ListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                  @Override
                  public void onScrollStateChanged (AbsListView view,int scrollState){
                      //  Log.d(LOG_TAG, "scrollState = " + scrollState);
                  }
                  @Override
                  public void onScroll (AbsListView view,int firstVisibleItem, int visibleItemCount, int totalItemCount){
                      // Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                      //         + ", visibleItemCount" + visibleItemCount
                      //         + ", totalItemCount" + totalItemCount);
                  }
        });

    }



    @Override //параметры меню "инфо"
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
    }

}






