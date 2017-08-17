package com.turki.androidapis.storage.filestorage;


import android.content.Context;
import android.os.Environment;
import android.os.Parcelable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Helper utility for saving data through external file.
 *
 * @author Turki Mahmoud
 */
public class ExternalFileSaveDataLayer {


    // TODO: You can change the key of the SharedPreference as you like.
    private static final String FILES_CACHE_DIRECTORY = "YOUR_FILES";

    /**
     * Saving object in file on external storage
     *
     * @param context
     * @param fileName
     * @param object
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void saveObject(Context context, String fileName, Object object)
            throws FileNotFoundException, IOException {
        if (!(object instanceof Serializable) && !(object instanceof Parcelable))
            throw new RuntimeException();
        if ((!isPresent()) || isReadOnly())
            throw new RuntimeException();

        File file = new File(getFilesDirectory(), fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream writer = new ObjectOutputStream(outputStream);
        writer.writeObject(object);
        writer.flush();
        writer.close();
    }

    /**
     * Getting object from file already saved on external storage
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object getObject(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        Object returnObj = null;
        if ((!isPresent()) || isReadOnly())
            throw new RuntimeException();

        FileInputStream inputStream = new FileInputStream(new File(getFilesDirectory(), fileName));
        ObjectInputStream writer = new ObjectInputStream(inputStream);
        returnObj = writer.readObject();
        writer.close();

        return returnObj;
    }

    /**
     * @return true or false for checking if there is external storage to save the data.
     */
    private static boolean isPresent() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @return true or false to check if you have permission to write on external storage
     */
    private static boolean isReadOnly() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
    }

    /**
     * @return file directory name from external storage
     */
    private static String getFilesDirectory() {
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + File.separator + FILES_CACHE_DIRECTORY);
        if (!dir.exists())
            dir.mkdir();

        return dir.getAbsolutePath();
    }
}
