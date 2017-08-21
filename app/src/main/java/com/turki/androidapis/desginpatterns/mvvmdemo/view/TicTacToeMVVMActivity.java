package com.turki.androidapis.desginpatterns.mvvmdemo.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.turki.tictactoe.R;
import com.turki.tictactoe.databinding.TictactoeDaggerBinding;
import com.turki.tictactoe.mvvmdemo.viewmodel.TicTacToeViewModel;

/**
 * The view binds to observable variables and actions exposed by the viewModel in a flexible way. More on that in minute.
 *
 * @author Turki Mahmoud
 */
public class TicTacToeMVVMActivity extends AppCompatActivity {

    TicTacToeViewModel viewModel = new TicTacToeViewModel();


    public static Intent getActivityIntent(Context context) {
        return new Intent(context, TicTacToeMVVMActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TictactoeDaggerBinding binding = DataBindingUtil.setContentView(this, R.layout.tictactoe_dagger);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
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
                viewModel.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
