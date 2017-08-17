package com.turki.androidapis.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.turki.androidapis.storage.sqlite.DataBaseGenericCreation.DatabaseManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * The application object is created before any of your Android components are started.
 * If you do not specify one in your AndroidManifest.xml file, the Android system creates a default object for you.
 *
 * @author Turki Mahmoud
 */
public class ApplicationController extends Application {
    private Handler mHandler;
    private ExecutorService mExecutorService;
    private static final int THREAD_POOL_SIZE = 3;
    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static ApplicationController app;

    /**
     * @return ApplicationController singleton instance
     */
    public synchronized static ApplicationController getInstance() {
        if (app == null)
            app = new ApplicationController();
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the singleton
        app = this;
        mHandler = new Handler();


        // initialize the database
        DatabaseManager.getInstance(getApplicationContext()).initializeTables();

        // Thread pool init
        mExecutorService = Executors.newScheduledThreadPool(THREAD_POOL_SIZE, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "Background Executors");
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public void runOnUiMainThread(Runnable runnable) {
        mHandler.post(runnable);

    }

    public void runInBackground(final Runnable runnable) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}