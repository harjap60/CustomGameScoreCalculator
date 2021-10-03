package ca.cmpt276.androidgamescoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    GameManager mainManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainManager = new GameManager();

        FloatingActionButton newBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("" + count);
                ++count;
            }
        });

    }

}