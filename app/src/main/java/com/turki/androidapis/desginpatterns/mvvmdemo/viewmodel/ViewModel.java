package com.turki.androidapis.desginpatterns.mvvmdemo.viewmodel;

/**
 * @author Turki Mahmoud
 */
public interface ViewModel {

    void onCreate();

    void onPause();

    void onResume();

    void onDestroy();

}
