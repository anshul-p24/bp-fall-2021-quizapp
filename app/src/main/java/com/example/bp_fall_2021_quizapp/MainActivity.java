package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatEditText;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Put class variables up here
    // Best practice is to make them private (can only be accessed within the class, or using getters/setters)
    // Each UI component that you want to reference needs a variable

    private AppCompatEditText nameInput;
    private Button buttonInput;
    /**
     * Method used to start an activity. It's the first method to run when the
     * activity begins
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obtain user's name using findViewById
        nameInput = findViewById(R.id.editTextTextPersonName2);
        buttonInput = findViewById(R.id.button2);

    }

    /**
     * Get username here and open Quiz Questions
     * @param view
     */
    public void startQuiz(View view){
        // set name variable every time user clicks "start"
        String name = nameInput.getText().toString();

        // If the name field is empty, prompt user to enter name
        if(name.isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        }

        // If user has entered name, begin quiz
        else {
            Intent intent = new Intent(this, QuizQuestionActivity.class);
            intent.putExtra("username",name);
            startActivity(intent);
            finish();
        }
        // close current activity
        }

    }
