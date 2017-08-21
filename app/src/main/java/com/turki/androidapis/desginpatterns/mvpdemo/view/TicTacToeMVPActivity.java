package com.turki.androidapis.desginpatterns.mvpdemo.view;

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

import com.turki.tictactoe.R;
import com.turki.tictactoe.mvpdemo.presenter.TicTacToePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * The only change here is that the Activity/Fragment is now considered part of the view.
 * We stop fighting the natural tendency for them to go hand in hand.
 * <p>
 * Good practice is to have the Activity implement a view interface so that the presenter has an interface to code to.
 * This eliminates coupling it to any specific view and allows simple unit testing with a mock implementation of the view.
 *
 * @author Turki Mahmoud
 */
public class TicTacToeMVPActivity extends AppCompatActivity implements TicTacToeView {

    private static String TAG = TicTacToeMVPActivity.class.getName();

    /* View Components referenced by the controller */
    @Bind(R.id.buttonGrid)
    ViewGroup buttonGrid;
    @Bind(R.id.winnerPlayerViewGroup)
    View winnerPlayerViewGroup;
    @Bind(R.id.winnerPlayerLabel)
    TextView winnerPlayerLabel;

    TicTacToePresenter presenter = new TicTacToePresenter(this);


    public static Intent getActivityIntent(Context context) {
        return new Intent(context, TicTacToeMVPActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);
        ButterKnife.bind(this);
        presenter.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCellClicked(View v) {

        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0, 1));
        int col = Integer.valueOf(tag.substring(1, 2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        presenter.onButtonSelected(row, col);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if (btn != null) {
            btn.setText(text);
        }
    }

    public void clearButtons() {
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }
}
