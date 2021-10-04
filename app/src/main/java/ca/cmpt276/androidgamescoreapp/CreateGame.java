package ca.cmpt276.androidgamescoreapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateGame extends AppCompatActivity {
    private GameManager mainManager;
    int player1Cards, player1Points, player1Wagers, player1Score;
    int player2Cards, player2Points, player2Wagers, player2Score;
    EditText player1CardsInput, player1PointsInput, player1WagersInput;
    EditText player2CardsInput, player2PointsInput, player2WagersInput;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar2);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        // Makes the actionbar back button return to the last activity on call stack instead of a new activity
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mainManager = GameManager.getInstance();
        player1CardsInput = (EditText) findViewById(R.id.player1_cards);
        player1PointsInput = (EditText) findViewById(R.id.player1_points);
        player1WagersInput = (EditText) findViewById(R.id.player1_wagers);

        player2CardsInput = (EditText) findViewById(R.id.player2_cards);
        player2PointsInput = (EditText) findViewById(R.id.player2_points);
        player2WagersInput = (EditText) findViewById(R.id.player2_wagers);

        saveButton = (Button) findViewById(R.id.save_game);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
        return true;
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, CreateGame.class);
    }
}