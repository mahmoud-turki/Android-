package com.turki.androidapis.storage.caching;

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
 * A helper caching utility used to save any object to internal storage, it makes more sense to save {@linkplain object}s
 * that are specific to your application in a storage area that other applications can access [Security wise - Usability wise]
 *
 * @author Turki Mahmoud
 */
public class CachingOnInternalStorage {


    // TODO: You can change the key of the SharedPreference as you like.
    private static final String FILES_CACHE_DIRECTORY = "YOUR_FILES";

    /**
     * Caches a java {@linkplain Object} internally within the application specific space.
     *
     * @param context
     * @param fileName The name which the file will be saved on disk.
     * @param object   The object to be saved, which must be {@linkplain Serializable} or {@linkplain Parcelable}
     */
    public static void cacheObject(Context context, String fileName, Object object) {
        try {
            saveObject(context, fileName, object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Restores a java {@linkplain Object} which is saved internally in the application specific space.
     *
     * @param context
     * @param fileName
     * @return The specified object or null if it doesn't exist.
     */
    public static Object restoreObject(Context context, String fileName) {
        Object returnObj = null;
        try {
            returnObj = getObject(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return returnObj;
    }


    /**
     * Saving object in file on internal storage
     *
     * @param context
     * @param fileName
     * @param object
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void saveObject(Context context, String fileName, Object object)
            throws FileNotFoundException, IOException {

        if (!(object instanceof Serializable) && !(object instanceof Parcelable))
            throw new RuntimeException();
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
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
    private static Object getObject(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
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
