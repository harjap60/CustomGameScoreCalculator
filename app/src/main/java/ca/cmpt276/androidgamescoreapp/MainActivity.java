package ca.cmpt276.androidgamescoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private GameManager mainManager;
    ArrayAdapter<Game> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateListView();

        // New Game button
        FloatingActionButton newBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textView3);
                textView.setText("" + count);
                ++count;

                Intent intent = CreateGame.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void populateListView() {
//        if(count > 0){
//            adapter.notifyDataSetChanged();
//        }
//        else{
            mainManager = GameManager.getInstance();
            Game newGame = new Game(); newGame.addPlayer(20,30,3); newGame.addPlayer(30,50,2);
            mainManager.addNewGame(newGame);
            newGame = new Game(); newGame.addPlayer(10,10,5); newGame.addPlayer(100,20,2);
            mainManager.addNewGame(newGame);
            newGame = new Game(); newGame.addPlayer(240,50,1); newGame.addPlayer(130,90,2);
            mainManager.addNewGame(newGame);
            newGame = new Game(); newGame.addPlayer(220,60,3); newGame.addPlayer(130,10,4);
            mainManager.addNewGame(newGame);
            newGame = new Game(); newGame.addPlayer(210,70,4); newGame.addPlayer(130,50,2);
            mainManager.addNewGame(newGame);

            //Build Adapter
            adapter = new ArrayAdapter<Game>(this, R.layout.game_items,R.id.item_textView ,mainManager.getGames());

            //Configure the list
            ListView list = (ListView) findViewById(R.id.gamesListView);
            list.setAdapter(adapter);
        //}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

}
