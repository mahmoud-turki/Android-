package com.turki.androidapis.desginpatterns.mvvmdemo.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.turki.tictactoe.model.Board;
import com.turki.tictactoe.model.Player;

/**
 * The ViewModel is responsible for wrapping the model and preparing observable data needed by the view.
 * It also provides hooks for the view to pass events to the model. The ViewModel is not tied to the view however.
 *
 * @author Turki Mahmoud
 */
public class TicTacToeViewModel implements ViewModel {

    private Board model;

    /**
     * These are observable variables that the viewModel will update as appropriate.
     * <p>
     * The view components are bound directly to these objects and react to changes
     * immediately, without the ViewModel needing to tell it to do so.
     * <p>
     * They don't have to be public, they could be private with a public getter method too.
     */
    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();

    public TicTacToeViewModel() {
        model = new Board();
    }

    /**
     * As with presenter, we implement standard lifecycle methods from the view
     * in case we need to do anything with our model during those events.
     */
    @Override
    public void onCreate() {

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
     * An Action, callable by the view.  This action will pass a message to the model
     * to restart and then clear the observable data in this ViewModel.
     */
    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    /**
     * An Action, callable by the view.  This action will pass a message to the model
     * for the cell clicked and then update the observable fields with the current
     * model state.
     */
    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

}
