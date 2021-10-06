package ca.cmpt276.androidgamescoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private GameManager mainManager;
    ArrayAdapter<Game> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainManager = GameManager.getInstance();
        adapter = new ArrayAdapter<Game>(this, R.layout.game_items,R.id.item_textView ,mainManager.getGames());

        //Configure the list
        list = (ListView) findViewById(R.id.gamesListView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = CreateGame.makeIntent(MainActivity.this, i);
                startActivity(intent);
            }
        });

        // New Game button(bottom right corner)
        FloatingActionButton newBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateGame.makeIntent(MainActivity.this, -1);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        list.invalidate();
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

}
