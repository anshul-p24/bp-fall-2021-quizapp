package com.example.bp_fall_2021_quizapp;

import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity {

    // UI components here
    private TextView question;
    private ProgressBar progressBar;
    private Button submitButton;
    private RadioGroup optionsRadioGroup;
    private RadioButton opt1;
    private RadioButton opt2;
    private RadioButton opt3;
    private RadioButton opt4;
    private TextView tvCurrentQuestionNumber;



    // other variables here
    private String name;
    private ArrayList<QuestionModel> questionList;
    private int qNum;
    private int numTotalQuestions;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        // create arraylist of questions
         questionList = new ArrayList<QuestionModel>();

        // get username intent from main activity screen
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("username");

        // initialize views using findViewByID
        question = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);
        submitButton = findViewById(R.id.button);
        optionsRadioGroup = findViewById(R.id.radioGroup);
        opt1 = findViewById(R.id.radioButton1);
        opt2 = findViewById(R.id.radioButton2);
        opt3 = findViewById(R.id.radioButton3);
        opt4 = findViewById(R.id.radioButton4);
        tvCurrentQuestionNumber = findViewById(R.id.textView2);

        // use helper method to add question content to arraylist
        addQuestions();

        // get total number of questions
        numTotalQuestions = questionList.size();
        qNum = 0;
        score = 0;

        // set progress bar
        progressBar.setProgress(0);
        progressBar.setMax(numTotalQuestions);

        // use helper method to proceed to next question
        showNextQuestion();
    }

    /**
     * Method that adds questions to our questions arraylist, using the Question Model constructor
     */
    private void addQuestions(){
        // question 1
        QuestionModel question1 = new QuestionModel("Select the correct syntax for an HTML comment",
                "<c> Is this the correct comment? </c>","<!-- Is this the correct comment? -->",
                "// Is this the correct comment?",
                "# Is this the correct comment?",2);
        questionList.add(question1);

        // question 2
        QuestionModel question2 = new QuestionModel("Select the correct syntax for a for loop in Java",
                "for (String i = 0; i < 10; i++)","for (int i = 0: i < 10: i++)",
                "for (int i = 0; i < 10; i++)",
                "for (String i = 0: i < 10: i++)",3);
        questionList.add(question2);

        // question 3
        QuestionModel question3 = new QuestionModel("Select the correct syntax for a Python function header",
                "def myMethod(num):","public int myMethod(int x)",
                "def myMethod(num);",
                "int myMethod(int x)",1);
        questionList.add(question3);

        // question 4
        QuestionModel question4 = new QuestionModel("Select the correct syntax for a Javascript variable",
        "String string = Arjun;","x = 0;",
                "const x = 0;",
                "let x = 0;",4);
        questionList.add(question4);

        // question 5
        QuestionModel question5 = new QuestionModel("Select the correct syntax for making a div blue in CSS",
                "color = blue;","div.color.setColor(blue);",
                "color.blue;",
                "div {background-color: blue}",4);
        questionList.add(question5);

    }

    /**
     * Check the answer when user clicks submit and move on to next question
     */
    public void submitQuestion(View view){
        // if no options have been selected, prompt user to select an answer
        if(optionsRadioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(getBaseContext(), "Please select an option", Toast.LENGTH_SHORT).show();
        }

        // use helper methods to check the answer and show the next question
        else {
            checkAnswer();
            showNextQuestion();
        }

    }

    /**
     * Display next question. If finished, move onto results screen
     */
    private void showNextQuestion(){

        // clear previous button selections

        // if you haven't gone through all the questions yet
            // set the question & text to the next question
            // increase question number
            // set progress bar
        if(qNum != numTotalQuestions) {
            qNum++;
            optionsRadioGroup.clearCheck();
            question.setText(questionList.get(qNum - 1).getQuestion());
            opt1.setText(questionList.get(qNum - 1).getOpt1());
            opt2.setText(questionList.get(qNum - 1).getOpt2());
            opt3.setText(questionList.get(qNum - 1).getOpt3());
            opt4.setText(questionList.get(qNum - 1).getOpt4());

            tvCurrentQuestionNumber.setText(Integer.toString(qNum - 1) + "/" + numTotalQuestions);
            progressBar.setProgress(qNum - 1);
        }

        // if finished with quiz, start Results activity
        else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("username",name);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }

    }

    /**
     * Checks the answer and increases score if correct
     */
    private void checkAnswer(){
        // see which answer they picked
        int selected = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));

        // increase score if correct
        if(selected == questionList.get(qNum - 1).getCorrectAnsNum()) {
            score++;
        }

    }
}