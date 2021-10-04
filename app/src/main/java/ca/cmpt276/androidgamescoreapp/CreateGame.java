package ca.cmpt276.androidgamescoreapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class CreateGame extends AppCompatActivity {
    private GameManager mainManager;

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