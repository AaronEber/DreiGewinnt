package com.example.dreigewinnt;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean blue = false;
    Boolean gameActive = true;
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
                blue = true;
                gamestat[position] = 0;

            } else {

                counter.setImageResource(R.drawable.redpokerchip);
                blue = false;
                gamestat[position] = 1;

            }
        }else {
            Toast.makeText(this, "Unentschieden", Toast.LENGTH_LONG).show();
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
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}