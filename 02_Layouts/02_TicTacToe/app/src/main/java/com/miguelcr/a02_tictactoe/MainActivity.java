package com.miguelcr.a02_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isPlayerOne = true;
    ImageView ivCell1, ivCell2, ivCell3, ivCell4, ivCell5, ivCell6, ivCell7, ivCell8, ivCell9;
    int[] selectedCells = {0,0,0,0,0,0,0,0,0};
    TextView currentPlayerText;
    boolean exist = false;
    String player1Name, player2Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get data from Intent
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        player1Name = extras.getString("player1");
        player2Name = extras.getString("player2");

        currentPlayerText = (TextView) findViewById(R.id.textViewPlayer);
        ivCell1 = (ImageView) findViewById(R.id.cell1);
        ivCell2 = (ImageView) findViewById(R.id.cell2);
        ivCell3 = (ImageView) findViewById(R.id.cell3);
        ivCell4 = (ImageView) findViewById(R.id.cell4);
        ivCell5 = (ImageView) findViewById(R.id.cell5);
        ivCell6 = (ImageView) findViewById(R.id.cell6);
        ivCell7 = (ImageView) findViewById(R.id.cell7);
        ivCell8 = (ImageView) findViewById(R.id.cell8);
        ivCell9 = (ImageView) findViewById(R.id.cell9);

        // Set the player 1 name by default
        currentPlayerText.setText(player1Name+" turn");
    }

    public void selectKomorka(View view) {
        int idCellSelected = view.getId();

        ImageView selectedImageView = null;
        int positionSelected = 0;

        switch (idCellSelected) {
            case R.id.cell1:
                selectedImageView = ivCell1;
                positionSelected = 0;
                break;
            case R.id.cell2:
                selectedImageView = ivCell2;
                positionSelected = 1;
                break;
            case R.id.cell3:
                selectedImageView = ivCell3;
                positionSelected = 2;
                break;
            case R.id.cell4:
                selectedImageView = ivCell4;
                positionSelected = 3;
                break;
            case R.id.cell5:
                selectedImageView = ivCell5;
                positionSelected = 4;
                break;
            case R.id.cell6:
                selectedImageView = ivCell6;
                positionSelected = 5;
                break;
            case R.id.cell7:
                selectedImageView = ivCell7;
                positionSelected = 6;
                break;
            case R.id.cell8:
                selectedImageView = ivCell8;
                positionSelected = 7;
                break;
            case R.id.cell9:
                selectedImageView = ivCell9;
                positionSelected = 8;
                break;
        } // end switch

        // check if is possible to select the cell where player clicked
        if(selectedCells[positionSelected]==0 && !exist) {
            // If now is playing player 1
            if (isPlayerOne) {
                selectedImageView.setImageResource(R.drawable.ic_player_1);
                selectedCells[positionSelected] = 1;
                existSolution();
                isPlayerOne = false;
            } else { // if is playing player 2
                selectedImageView.setImageResource(R.drawable.ic_player_2);
                selectedCells[positionSelected] = 2;
                existSolution();
                isPlayerOne = true;
            }
        } else {
            Toast.makeText(this, "Sorry, try with other cell!", Toast.LENGTH_SHORT).show();
        }




    } // end selectKomorka method

    public void existSolution() {

        // c1,c2,c3 > 0,1,2
        if(selectedCells[0]==selectedCells[1]
                && selectedCells[1]==selectedCells[2]
                && (selectedCells[0]==1 || selectedCells[0]==2)) {
            exist = true;
        } else if(selectedCells[3]==selectedCells[4]
                && selectedCells[4]==selectedCells[5]
                && (selectedCells[3]==1 || selectedCells[3]==2)) { // c4,c5,c6 > 3,4,5
            exist = true;
        } else if(selectedCells[6]==selectedCells[7]
                && selectedCells[7]==selectedCells[8]
                && (selectedCells[6]==1 || selectedCells[6]==2)) { // c7,c8,c9 > 6,7,8
            exist = true;
        } else if(selectedCells[0]==selectedCells[3]
                && selectedCells[3]==selectedCells[6]
                && (selectedCells[0]==1 || selectedCells[0]==2)) { // c1,c4,c7 > 0,3,6
            exist = true;
        } else if(selectedCells[1]==selectedCells[4]
                && selectedCells[4]==selectedCells[7]
                && (selectedCells[1]==1 || selectedCells[1]==2)) { // c2,c5,c8 > 1,4,7
            exist = true;
        } else if(selectedCells[2]==selectedCells[5]
                && selectedCells[5]==selectedCells[8]
                && (selectedCells[2]==1 || selectedCells[2]==2)) { // c3,c6,c9 > 2,5,8
            exist = true;
        } else if(selectedCells[0]==selectedCells[4]
                && selectedCells[4]==selectedCells[8]
                && (selectedCells[0]==1 || selectedCells[0]==2)) { // c1,c5,c9 > 0,4,8
            exist = true;
        } else if(selectedCells[2]==selectedCells[4]
                && selectedCells[4]==selectedCells[6]
                && (selectedCells[2]==1 || selectedCells[2]==2)) { // c3,c5,c7 > 2,4,6
            exist = true;
        }

        if(exist) {
            if(isPlayerOne) {
                currentPlayerText.setText(player1Name+" wins!");
            } else {
                currentPlayerText.setText(player2Name+" wins!");
            }
        } else { // if not exist a solution

            exist = true;
            for(int i=0; i<9; i++) {
                if(selectedCells[i]==0)
                    exist = false;
            }

            if(!exist) {
                // If exist == false is because we can continue playing.
                // Exist at least one more cell with 0 value
                if(isPlayerOne) {
                    currentPlayerText.setText(player1Name+" turn");
                } else {
                    currentPlayerText.setText(player2Name+" turn");
                }
            } else {
                currentPlayerText.setText("Game over!");
            }

        }
    }

} // end MainActivity
