package com.example.dreigewinnt;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Boolean blue = false;
    Boolean gameActive = true;

    Button playAgainButton;
    TextView winnerTextView;

    //2 ist nichts, 0 ist blau, 1 ist rot
    int [] gamestat = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2,5,8}};


    public void dropIn (View view) {

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int position = (int) counter.getTag();


        if (gamestat[position] ==2 && gameActive) {

            if (blue) {

                counter.setImageResource(R.drawable.bluepokerchip);
                blue = false;
                gamestat[position] = 0;

            } else {

                counter.setImageResource(R.drawable.redpokerchip);
                blue = true;
                gamestat[position] = 1;

            }
        }else {
            Toast.makeText(this, "Unentschieden", Toast.LENGTH_LONG).show();
            playAgainButton.setVisibility(View.VISIBLE);

        }
        counter.animate().translationXBy(1500).setDuration(300);


        for(int[] winningsPosition:winningpositions) {

            if(gamestat[winningsPosition[0]] == gamestat[winningsPosition[1]] && gamestat[winningsPosition[1]] == gamestat[winningsPosition[2]] && gamestat[winningsPosition[0]] != 2){

                String winner;

                if(blue) {

                    winner = "Blau";

                }else{

                    winner = "Rot";
                }

                Toast.makeText(this, winner + " hat gewonnen!", Toast.LENGTH_LONG).show();
                gameActive = false;

                winnerTextView.setText(winner + " hat gewonnen!");

                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);

            }
        }

    }

    public void playAgain(View view) {

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.board);

        for(int i= 0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gamestat, 2);

        gameActive = true;
        blue = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
    }
}