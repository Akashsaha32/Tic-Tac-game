package com.layout.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0= zero, 1 = cross
    String msg = "";
    int gameActive = 1;
    int playerZero = 0;
    int playerCross = 0;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winingPositions = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };
    public void drop(View view){
        ImageView counter = (ImageView) view;
        int clicked = Integer.parseInt(counter.getTag().toString());
        if(gameState[clicked]== 2 && gameActive == 1){
            gameState[clicked] = activePlayer;
            counter.setAlpha(0f);
            if(activePlayer == 0){
                activePlayer = 1;
                playerZero++;
                counter.setImageResource(R.drawable.pic2);
            }else {
                activePlayer = 0;
                playerCross++;
                counter.setImageResource(R.drawable.pic1);
            }
            counter.animate().alpha(1.0f).setDuration(100);
        }
        if((playerCross >= 3) || (playerZero >= 3)){
            for(int[] winingposition : winingPositions){
                if(gameState[winingposition[0]] == gameState[winingposition[1]] &&
                    gameState[winingposition[1]] == gameState[winingposition[2]] &&
                    gameState[winingposition[0]] != 2){
                    //someone win...........
                    gameActive = 0;
                    if(gameState[winingposition[0]] == 0){
                        msg = "Player 0 won";
                    }else{
                        msg = "Player 1 Won";
                    }
                    LinearLayout layout = (LinearLayout) findViewById(R.id.endPart);
                    layout.setVisibility(View.VISIBLE);
                    TextView showResult = (TextView) findViewById(R.id.winingReport);
                    showResult.setText(msg);
                    break;
                }else{
                    // when match is drow...
                    int gameIsOver = 1;
                    for(int i : gameState){
                        if(i == 2){
                            gameIsOver = 0;
                        }
                    }
                    if(gameIsOver == 1){
                        msg = "Match is Draw";
                        LinearLayout layout = (LinearLayout) findViewById(R.id.endPart);
                        layout.setVisibility(View.VISIBLE);
                        TextView showResult = (TextView) findViewById(R.id.winingReport);
                        showResult.setText(msg);
                    }
                }
            }
        }
    }

    public void tryAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.endPart);
        layout.setVisibility(View.INVISIBLE);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.buttonShow);
        relativeLayout.setVisibility(View.INVISIBLE);
        gameActive = 1;
        for(int i = 0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i< gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    public void exit(View view){
        System.exit(0);
    }

    public void showGameState(View view){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.endPart);
        linearLayout.setVisibility(View.INVISIBLE);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.buttonShow);
        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
