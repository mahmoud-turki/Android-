package com.turki.androidapis.desginpatterns.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.turki.androidapis.R;
import com.turki.androidapis.desginpatterns.model.Board;
import com.turki.androidapis.desginpatterns.model.Player;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * The controller is Glue that ties the app together. It’s the master controller for what happens in the application.
 * When the View tells the controller that a user clicked a button,
 * the controller decides how to interact with the model accordingly.
 * <p>
 * Based on data changing in the model, the controller may decide to update the state of the view as appropriate.
 * In the case of an Android application, the controller is almost always represented by an Activity or Fragment.
 *
 * @author Turki Mahmoud
 */
public class TicTacToeMVCActivity extends AppCompatActivity {

    private static String TAG = TicTacToeMVCActivity.class.getName();

    private Board model;


    /* View Components referenced by the controller */
    @Bind(R.id.buttonGrid)
    ViewGroup buttonGrid;
    @Bind(R.id.winnerPlayerViewGroup)
    View winnerPlayerViewGroup;
    @Bind(R.id.winnerPlayerLabel)
    TextView winnerPlayerLabel;

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, TicTacToeMVCActivity.class);
    }

    /**
     * In onCreate of the Activity we lookup & retain references to view components
     * and instantiate the model.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        ButterKnife.bind(this);

        model = new Board();
    }

    /**
     * Here we inflate and attach our reset button in the menu.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }

    /**
     * We tie the reset() action to the reset tap event.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * When the view tells us a cell is clicked in the tic tac toe board,
     * this method will fire. We update the model and then interrogate it's state
     * to decide how to proceed.  If X or O won with this move, update the view
     * to display this and otherwise mark the cell that was clicked.
     *
     * @param v
     */
    public void onCellClicked(View v) {

        Button button = (Button) v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            button.setText(playerThatMoved.toString());

            if (model.getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
            }
        }

    }
    /**
     * On reset, we clear the winner label and hide it, then clear out each button.
     * We also tell the model to reset (restart) it's state.
     */
    private void reset() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        model.restart();

        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }


}
