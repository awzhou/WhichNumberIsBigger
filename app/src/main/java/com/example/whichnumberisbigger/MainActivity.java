package com.example.whichnumberisbigger;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewScore;
    private int score;
    private Button buttonLeft;
    private int leftNum;
    private Button buttonRight;
    private int rightNum;
    private ConstraintLayout constraintLayout;

    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;

        wireWidgets();

        randomizeAndUpdateDisplay();
    }

    /**
     * Wires widgets
     */
    private void wireWidgets() {
        textViewScore = findViewById(R.id.textview_main_score);

        buttonLeft = findViewById(R.id.button_main_number1);

        buttonRight = findViewById(R.id.button_main_number2);

        constraintLayout = findViewById(R.id.constraintlayout_main);
    }

    /**
     * Generates a random number between MIN_NUM and MAX_NUM, inclusive
     * @return the random number
     */
    private int generateNum() {
        int range = MAX_NUM - MIN_NUM + 1;

        return (int) (range * Math.random()) + MIN_NUM;
    }

    /**
     * Generates two random numbers and checks to make sure they aren't the same
     * Updates score
     * Changes background to random color
     */
    private void randomizeAndUpdateDisplay() {


        leftNum = generateNum();

        rightNum = generateNum();

        if (rightNum == leftNum) {
            randomizeAndUpdateDisplay();
        }

        textViewScore.setText("Score: " + score);

        buttonLeft.setText(String.valueOf(leftNum));

        buttonRight.setText(String.valueOf(rightNum));

        int r = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);

        constraintLayout.setBackgroundColor(Color.rgb(r,g,b));
        textViewScore.setTextColor(Color.rgb(255-r, 255-g, 255-b));
    }

    /**
     * Checks the accuracy of the answer
     * @param leftPressed whether the left button was pressed
     */
    public void checkAnswer(boolean leftPressed) {
        String message;
        if((leftNum > rightNum && leftPressed) || rightNum > leftNum && !leftPressed) {
            score++;
            message = "Correct!";
        }
        else {
            score--;
            message = "Incorrect.";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        randomizeAndUpdateDisplay();
    }

    /**
     * Do this when left button is clicked
     * @param view the screen
     */
    public void onLeftClick(View view) {
        checkAnswer(true);
    }

    /**
     * Do this when right button is clicked
     * @param view the screen
     */
    public void onRightClick(View view) {
        checkAnswer(false);
    }
}
