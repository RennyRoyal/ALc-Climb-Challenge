package com.example.rennymark.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[5][5];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView txtViewPlayer1;
    private TextView txtViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtViewPlayer1 = findViewById(R.id.txt_p1);
        txtViewPlayer2 = findViewById(R.id.txt_p2);

        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                String btnID = "btn_" +i +j;
                int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnreset = findViewById(R.id.btn_reset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button)view).setText("X");
        } else {
            ((Button)view).setText("O");
        }

        roundCount++;

        if (checkWinner()){
            if (player1Turn){
                player1Wins();
            }else {
                player2Wins();
            }
        }else if (roundCount == 25){
            draw();
        }else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkWinner(){
        String[][] field = new String [5][5];

        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i=0; i<5; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && field[i][0].equals(field[i][3])
                    && field[i][0].equals(field[i][4])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i=0; i<5; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i])
                    && field[0][i].equals(field[4][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && field[0][0].equals(field[4][4])
                && !field[0][0].equals("")){
            return true;
        }

        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2])
                && field[0][4].equals(field[3][1])
                && field[0][4].equals(field[4][0])
                && !field[0][4].equals("")){
            return true;
        }
        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(this,"Player 1 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this,"Player 2 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "It is a Tie!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        txtViewPlayer1.setText("Player 1 : " +player1Points);
        txtViewPlayer2.setText("Player 2 : " +player2Points);
    }

    private void resetBoard() {
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }
}
