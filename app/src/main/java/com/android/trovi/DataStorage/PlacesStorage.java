package com.android.trovi.DataStorage;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlacesStorage {
    public void createFile(String latitude,String longitude){

        String namePlace = latitude+","+longitude;

        String directory_path = Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/eliancap66/Places/";

        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        File file2 = new File(directory_path, "listPlaces.txt");


        //Write to file
        try (FileWriter afileWriter = new FileWriter(file2,true)) {
            afileWriter.append(namePlace+"\n");
        } catch (IOException e) {
            //Handle exception
        }

    }
}
