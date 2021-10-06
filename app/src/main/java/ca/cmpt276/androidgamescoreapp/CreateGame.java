package ca.cmpt276.androidgamescoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class CreateGame extends AppCompatActivity {
    private GameManager mainManager;
    int p1Cards, p1Points, p1Wagers, p2Cards, p2Points, p2Wagers;
    EditText p1CardsInput, p1PointsInput, p1WagersInput, p2CardsInput, p2PointsInput, p2WagersInput;
    TextView player1_score, player2_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        mainManager = GameManager.getInstance();

        //Setup the actionbar
        Toolbar toolbar2 = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar2);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        // Makes the actionbar back button return to the last activity on call stack instead of a new activity
        toolbar2.setNavigationOnClickListener(v -> finish());

        // Sets title if a game is being edited and sets the date and time
        int index = getIntent().getIntExtra("index", 0);
        TextView dateTime = findViewById(R.id.date_time);
        String pattern = "hh:mma";
        if (index >= 0) {
            setTitle("Edit Game");
            Game c = mainManager.getGame(index);
            dateTime.setText(c.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " +
                    c.getDate().getDayOfMonth() + " @ " + c.getTime().format(DateTimeFormatter.ofPattern(pattern)));
        } else {
            LocalTime time = LocalTime.now();
            LocalDate date = LocalDate.now();
            dateTime.setText(date.getMonth().getDisplayName(TextStyle.SHORT, Locale.US) + " " +
                    date.getDayOfMonth() + " @ " + time.format(DateTimeFormatter.ofPattern(pattern)));
        }

        // Set EditText fields to corresponding editText boxes
        p1CardsInput = findViewById(R.id.player1_cards);
        p1PointsInput = findViewById(R.id.player1_points);
        p1WagersInput = findViewById(R.id.player1_wagers);
        player1_score = findViewById(R.id.player1_score);

        p2CardsInput = findViewById(R.id.player2_cards);
        p2PointsInput = findViewById(R.id.player2_points);
        p2WagersInput = findViewById(R.id.player2_wagers);
        player2_score = findViewById(R.id.player2_score);

        // Load previous data if editing a game
        if (index >= 0) {
            Game editGame = mainManager.getGame(index);
            p1CardsInput.setText("" + editGame.getPlayer_list().getFirst().getCards());
            p1PointsInput.setText("" + editGame.getPlayer_list().getFirst().getCards_sum());
            p1WagersInput.setText("" + editGame.getPlayer_list().getFirst().getWager_cards());
            player1_score.setText("" + editGame.getPlayer_list().getFirst().getPoints());

            p2CardsInput.setText("" + editGame.getPlayer_list().getLast().getCards());
            p2PointsInput.setText("" + editGame.getPlayer_list().getLast().getCards_sum());
            p2WagersInput.setText("" + editGame.getPlayer_list().getLast().getWager_cards());
            player2_score.setText("" + editGame.getPlayer_list().getLast().getPoints());
        }

        // Assign text change listeners to change score while user changes editText boxes
        p1CardsInput.addTextChangedListener(new GenericTextWatcher(p1CardsInput));
        p1PointsInput.addTextChangedListener(new GenericTextWatcher(p1PointsInput));
        p1WagersInput.addTextChangedListener(new GenericTextWatcher(p1WagersInput));

        p2CardsInput.addTextChangedListener(new GenericTextWatcher(p2CardsInput));
        p2PointsInput.addTextChangedListener(new GenericTextWatcher(p2PointsInput));
        p2WagersInput.addTextChangedListener(new GenericTextWatcher(p2WagersInput));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_game, menu);
        return true;
    }

    /* +
    Function for taking care of saving the game.
    Also accounts for saving and edited game
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_game: {
                if (!TextUtils.isEmpty(p1CardsInput.getText().toString().trim())) {
                    p1Cards = Integer.valueOf(p1CardsInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 1 Cards is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (!TextUtils.isEmpty(p1PointsInput.getText().toString().trim())) {
                    p1Points = Integer.valueOf(p1PointsInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 1 Points is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (!TextUtils.isEmpty(p1WagersInput.getText().toString().trim())) {
                    p1Wagers = Integer.valueOf(p1WagersInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 1 Wagers is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (!TextUtils.isEmpty(p2CardsInput.getText().toString().trim())) {
                    p2Cards = Integer.valueOf(p2CardsInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 2 Cards is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (!TextUtils.isEmpty(p2PointsInput.getText().toString().trim())) {
                    p2Points = Integer.valueOf(p2PointsInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 2 Points is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (!TextUtils.isEmpty(p2WagersInput.getText().toString().trim())) {
                    p2Wagers = Integer.valueOf(p2WagersInput.getText().toString());
                } else {
                    Toast.makeText(this, "Player 2 Wagers is invalid.", Toast.LENGTH_SHORT).show();
                    return true;
                }

                // If the current activity is an edit of a game, change the fields of the item and end activity
                int index = getIntent().getIntExtra("index", 0);
                if (index >= 0) {
                    Game editGame = mainManager.getGame(index);
                    editGame.removePlayer(0);
                    editGame.removePlayer(0);
                    editGame.addPlayer(p1Cards, p1Points, p1Wagers);
                    editGame.addPlayer(p2Cards, p2Points, p2Wagers);
                    finish();
                    return true;
                }
                // If the current activity is a new game, add it to the arrayList and end activity
                Game newGame = new Game();
                newGame.addPlayer(p1Cards, p1Points, p1Wagers);
                newGame.addPlayer(p2Cards, p2Points, p2Wagers);
                mainManager.addNewGame(newGame);
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* +
    Intent function is used to accept variable index for the listView.
    Helps to identify if the current activity is for a new game or edit game
     */
    public static Intent makeIntent(Context context, int i) {
        Intent intent = new Intent(context, CreateGame.class);
        intent.putExtra("index", i);
        return intent;
    }

    /* +
    Got some help for watching editText change from this stackoverflow page.
    https://stackoverflow.com/questions/5702771/how-to-use-single-textwatcher-for-multiple-edittexts
     */
    class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.player1_cards:
                case R.id.player1_points:
                case R.id.player1_wagers:
                    if (TextUtils.isEmpty(p1PointsInput.getText().toString().trim()) ||
                            TextUtils.isEmpty(p1WagersInput.getText().toString().trim()) ||
                            TextUtils.isEmpty(p1CardsInput.getText().toString().trim())) {
                        player1_score.setText("N/A");
                    } else {
                        p1Cards = Integer.valueOf(p1CardsInput.getText().toString());
                        p1Points = Integer.valueOf(p1PointsInput.getText().toString());
                        p1Wagers = Integer.valueOf(p1WagersInput.getText().toString());
                        PlayerScore newPlayer = new PlayerScore(p1Cards, p1Points, p1Wagers);
                        player1_score.setText("" + newPlayer.getPoints());
                    }
                    break;

                case R.id.player2_cards:
                case R.id.player2_points:
                case R.id.player2_wagers:
                    if (TextUtils.isEmpty(p2PointsInput.getText().toString().trim()) ||
                            TextUtils.isEmpty(p2WagersInput.getText().toString().trim()) ||
                            TextUtils.isEmpty(p2CardsInput.getText().toString().trim())) {
                        player2_score.setText("N/A");
                    } else {
                        p2Cards = Integer.valueOf(p2CardsInput.getText().toString());
                        p2Points = Integer.valueOf(p2PointsInput.getText().toString());
                        p2Wagers = Integer.valueOf(p2WagersInput.getText().toString());
                        PlayerScore newPlayer = new PlayerScore(p2Cards, p2Points, p2Wagers);
                        player2_score.setText("" + newPlayer.getPoints());
                    }
            }
        }
    }
}