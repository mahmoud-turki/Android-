package com.turki.androidapis.desginpatterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.turki.tictactoe.mvcdemo.controller.TicTacToeMVCActivity;
import com.turki.tictactoe.mvpdemo.view.TicTacToeMVPActivity;
import com.turki.tictactoe.mvvmdemo.view.TicTacToeMVVMActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.mvcDemoBtn)
    Button mvcDemoBtn;
    @Bind(R.id.mvpDemoBtn)
    Button mvpDemoBtn;
    @Bind(R.id.mvvmDemoBtn)
    Button mvvmDemoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.mvcDemoBtn)
    public void mvcDemoClick(Button button) {
        startActivity(TicTacToeMVCActivity.getActivityIntent(this));
    }

    @OnClick(R.id.mvpDemoBtn)
    public void mvpDemoClick(Button button) {
        startActivity(TicTacToeMVPActivity.getActivityIntent(this));
    }

    @OnClick(R.id.mvvmDemoBtn)
    public void mvvmDemoClick(Button button) {
        startActivity(TicTacToeMVVMActivity.getActivityIntent(this));
    }
}
