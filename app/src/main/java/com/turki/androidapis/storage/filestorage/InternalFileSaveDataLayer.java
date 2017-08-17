package com.turki.androidapis.storage.filestorage;

import android.content.Context;
import android.os.Parcelable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Helper utility for saving data through internal file.
 *
 * @author Turki Mahmoud
 */
public class InternalFileSaveDataLayer {

    /**
     * Saving object in file on internal storage
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
        FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream writer = new ObjectOutputStream(outputStream);

        writer.writeObject(object);
        writer.flush();
        writer.close();
    }
}
