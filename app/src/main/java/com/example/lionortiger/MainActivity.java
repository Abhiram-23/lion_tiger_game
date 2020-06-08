package com.example.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum Player {
        ONE, TWO, No
    }
    Player currentPlayer = Player.ONE;
    Player[] playeChoices = new Player[9];
    int[][] winnerRowColumn = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Boolean gameOver = false;
    private Button btnreset;
    private GridLayout   gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playeChoices[0] = Player.No;
        playeChoices[1] = Player.No;
        playeChoices[2] = Player.No;
        playeChoices[3] = Player.No;
        playeChoices[4] = Player.No;
        playeChoices[5] = Player.No;
        playeChoices[6] = Player.No;
        playeChoices[7] = Player.No;
        playeChoices[8] = Player.No;
        btnreset = findViewById(R.id.buttonReset);
        gridLayout = findViewById(R.id.gridLayout);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();

            }
        });
    }

    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int tpTag = Integer.parseInt(tappedImageView.getTag().toString());
        if (playeChoices[tpTag] == Player.No && gameOver  == false) {
            tappedImageView.setTranslationX(-2000);
            playeChoices[tpTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotationX(3600).setDuration(500);
            Toast.makeText(this, tappedImageView.getTag().toString(),Toast.LENGTH_SHORT).show();
            for (int[] winnerColumns : winnerRowColumn) {
                if (playeChoices[winnerColumns[0]] == playeChoices[winnerColumns[1]] && playeChoices[winnerColumns[1]] == playeChoices[winnerColumns[2]] && playeChoices[winnerColumns[0]] != Player.No) {
                    btnreset.setVisibility(View.VISIBLE);
                    gameOver  = true;
                    Toast.makeText(this, tappedImageView.getTag().toString(),
                            Toast.LENGTH_SHORT).show();                }
            }
        }
    }
    //reset game function
    private void resetTheGame() {
        for(int index=0 ;index<gridLayout.getChildCount();index++ ){
            ImageView imageView= (ImageView)gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
            }
            currentPlayer=Player.ONE;
            playeChoices[0] = Player.No;
            playeChoices[1] = Player.No;
            playeChoices[2] = Player.No;
            playeChoices[3] = Player.No;
            playeChoices[4] = Player.No;
            playeChoices[5] = Player.No;
            playeChoices[6] = Player.No;
            playeChoices[7] = Player.No;
            playeChoices[8] = Player.No;
            gameOver =false;
            btnreset.setVisibility(View.GONE);
    }
}