package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {

    private String name;
    private int score;

    // UI component variables
    private TextView result;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // initialize UI components
        result = findViewById(R.id.textView9);
        playAgainButton = findViewById(R.id.button3);

        // set username and score
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("username");
        score = bundle.getInt("score");
        result.setText("Name: " + name + "\nScore: " + score);
    }

    /**
     * Restarts the quiz so you can play another round
     * @param view
     */
    public void restart(View view) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}