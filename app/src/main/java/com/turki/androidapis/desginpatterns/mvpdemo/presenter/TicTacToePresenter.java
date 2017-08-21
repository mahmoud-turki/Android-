package com.turki.androidapis.desginpatterns.mvpdemo.presenter;


import com.turki.tictactoe.model.Board;
import com.turki.tictactoe.model.Player;
import com.turki.tictactoe.mvpdemo.view.TicTacToeView;

/**
 * This is essentially the controller from MVC except that it is not at all tied to the View, just an interface.
 * <p>
 * This addresses the testability concerns as well as the modularity/flexibility concerns we had with MVC.
 * In fact, MVP purists would argue that the presenter should never have any references to any Android APIs or code.
 *
 * @author Turki Mahmoud
 */
public class TicTacToePresenter implements Presenter {

    private TicTacToeView view;
    private Board model;

    public TicTacToePresenter(TicTacToeView view) {
        this.view = view;
        this.model = new Board();
    }

    /**
     * Here we implement delegate methods for the standard Android Activity Lifecycle.
     * These methods are defined in the Presenter interface that we are implementing.
     */
    @Override
    public void onCreate() {
        model = new Board();
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestroy() {
    }

    /**
     * When the user selects a cell, our presenter only hears about
     * what was (row, col) pressed, it's up to the view now to determine that from
     * the Button that was pressed.
     */
    public void onButtonSelected(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }

    /**
     * When we need to reset, we just dictate what to do.
     */
    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }
}
