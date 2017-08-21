package com.turki.androidapis.desginpatterns.mvpdemo.view;

/**
 *
 * @author Turki Mahmoud
 */
public interface TicTacToeView {
    void showWinner(String winningPlayerDisplayLabel);

    void clearWinnerDisplay();

    void clearButtons();

    void setButtonText(int row, int col, String text);
}
